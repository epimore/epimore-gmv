package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.service.api.SseApi;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@Service
public class SseApiImpl implements SseApi {
    private final static Logger logger = LoggerFactory.getLogger(SseApiImpl.class);

    // 用户id和emitter的映射
    private static final ConcurrentHashMap<String, SseEmitter> SSE_CACHE = new ConcurrentHashMap<>();
    // 租户id和用户id的映射
    private static final ConcurrentHashMap<String, Set<String>> SSE_TENANT_CACHE = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ScheduledFuture<?>> HEARTBEAT_TASKS = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(
            Runtime.getRuntime().availableProcessors() * 2
    );

    @Override
    public SseEmitter connect(String uid, String tenantIds) {
        // 清理可能存在的旧连接
        close(uid);

        SseEmitter emitter = createSseEmitter(uid);
        SSE_CACHE.put(uid, emitter);

        // 启动心跳
        startHeartbeat(emitter, uid);

        // 延迟发送初始化消息
        scheduler.schedule(() -> sendInitMsg(emitter, uid), 1, TimeUnit.SECONDS);

        // 映射租户关系
        mapTenant(uid, tenantIds);

        logger.debug("SSE连接建立成功, uid: {}, tenantIds: {}", uid, tenantIds);
        return emitter;
    }

    private SseEmitter createSseEmitter(String uid) {
        SseEmitter emitter = new SseEmitter(10 * 60 * 1000L); // 10分钟超时

        emitter.onCompletion(() -> {
            cleanupConnection(uid);
            logger.debug("连接完成回调，uid = {}", uid);
        });

        emitter.onTimeout(() -> {
            cleanupConnection(uid);
            logger.debug("连接超时，uid = {}", uid);
        });

        emitter.onError(throwable -> {
            cleanupConnection(uid);
            logger.debug("连接异常，uid = {}, error = {}", uid, throwable.getMessage());
        });

        return emitter;
    }

    private void cleanupConnection(String uid) {
        SseEmitter emitter = SSE_CACHE.remove(uid);
        if (emitter != null) {
            try {
                emitter.complete();
            } catch (Exception e) {
                logger.debug("完成emitter时发生异常: {}", e.getMessage());
            }
        }

        // 停止心跳任务
        ScheduledFuture<?> heartbeatTask = HEARTBEAT_TASKS.remove(uid);
        if (heartbeatTask != null) {
            heartbeatTask.cancel(false);
        }

        // 清理租户映射
        cleanupTenantMapping(uid);

        logger.debug("连接清理完成, uid: {}", uid);
    }

    private void cleanupTenantMapping(String uid) {
        Iterator<Map.Entry<String, Set<String>>> iterator = SSE_TENANT_CACHE.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> entry = iterator.next();
            Set<String> userSet = entry.getValue();
            userSet.remove(uid);

            // 如果租户没有用户了，清理租户条目
            if (userSet.isEmpty()) {
                iterator.remove();
                logger.debug("清理空租户: {}", entry.getKey());
            }
        }
    }

    private void startHeartbeat(SseEmitter emitter, String uid) {
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {
            if (!SSE_CACHE.containsKey(uid)) {
                // 连接已关闭，停止心跳
                ScheduledFuture<?> task = HEARTBEAT_TASKS.remove(uid);
                if (task != null) {
                    task.cancel(false);
                }
                return;
            }

            try {
                Map<String, Object> heartbeatMsg = new HashMap<>();
                heartbeatMsg.put("type", "heartbeat");
                heartbeatMsg.put("data", "💓");
                heartbeatMsg.put("timestamp", System.currentTimeMillis());

                emitter.send(SseEmitter.event()
                        .id(UUID.randomUUID().toString())
                        .name("heartbeat")
                        .data(JSON.toJSONString(heartbeatMsg))
                        .reconnectTime(5000L));

                logger.debug("心跳发送成功, uid: {}", uid);
            } catch (Exception e) {
                logger.debug("心跳发送失败，清理连接 uid = {}, error = {}", uid, e.getMessage());
                cleanupConnection(uid);
            }
        }, 30, 30, TimeUnit.SECONDS);

        HEARTBEAT_TASKS.put(uid, future);
    }

    private void mapTenant(String uid, String tenantIds) {
        if (ObjectUtils.isNotEmpty(tenantIds)) {
            for (String tenantId : tenantIds.split(",")) {
                if (ObjectUtils.isNotEmpty(tenantId)) {
                    Set<String> tenantIdSet = SSE_TENANT_CACHE.computeIfAbsent(tenantId, key ->
                            Collections.synchronizedSet(new HashSet<>())); // 使用同步Set
                    tenantIdSet.add(uid);
                    logger.debug("映射用户到租户, uid: {}, tenantId: {}", uid, tenantId);
                }
            }
        }
    }

    @Override
    public void close(String uid) {
        logger.debug("手动关闭SSE连接, uid: {}", uid);
        cleanupConnection(uid);
    }

    @Override
    public void sendMsg(String uid, Object obj) {
        if (StringUtils.isEmpty(uid)) {
            // 广播给所有用户 - 避免并发修改异常
            List<String> uids = new ArrayList<>(SSE_CACHE.keySet());
            for (String key : uids) {
                SseEmitter emitter = SSE_CACHE.get(key);
                if (emitter != null) {
                    sendMsg(emitter, obj, key);
                }
            }
        } else {
            SseEmitter emitter = SSE_CACHE.get(uid);
            if (emitter != null) {
                sendMsg(emitter, obj, uid);
            } else {
                logger.debug("用户不在线，无法发送消息, uid: {}", uid);
            }
        }
    }

    @Override
    public void sendDeviceMsg(String tenantId, Object msg) {
        Set<String> userSet = SSE_TENANT_CACHE.get(tenantId);
        if (userSet == null || userSet.isEmpty()) {
            logger.debug("租户没有在线用户, tenantId: {}", tenantId);
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("type", "device");
        map.put("data", msg);
        map.put("timestamp", System.currentTimeMillis());

        // 使用副本避免并发修改
        Set<String> userCopy = new HashSet<>(userSet);
        int successCount = 0;
        int failCount = 0;

        for (String uid : userCopy) {
            if (sendMsgToUser(uid, map)) {
                successCount++;
            } else {
                failCount++;
            }
        }

        logger.debug("设备消息发送完成, tenantId: {}, 成功: {}, 失败: {}", tenantId, successCount, failCount);
    }

    @Override
    public void sendNotifyMsg(String tenantId, Object msg) {
        Set<String> userSet = SSE_TENANT_CACHE.get(tenantId);
        if (userSet == null || userSet.isEmpty()) {
            logger.debug("租户没有在线用户, tenantId: {}", tenantId);
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("type", "notify");
        map.put("data", msg);
        map.put("timestamp", System.currentTimeMillis());

        Set<String> userCopy = new HashSet<>(userSet);
        int successCount = 0;
        int failCount = 0;

        for (String uid : userCopy) {
            if (sendMsgToUser(uid, map)) {
                successCount++;
            } else {
                failCount++;
            }
        }

        logger.debug("通知消息发送完成, tenantId: {}, 成功: {}, 失败: {}", tenantId, successCount, failCount);
    }

    /**
     * 发送消息给指定用户
     */
    private boolean sendMsgToUser(String uid, Object msg) {
        SseEmitter emitter = SSE_CACHE.get(uid);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(UUID.randomUUID().toString())
                        .data(JSON.toJSONString(msg))
                        .reconnectTime(5000L));
                return true;
            } catch (Exception e) {
                logger.debug("发送消息失败，清理连接 uid = {}, error = {}", uid, e.getMessage());
                cleanupConnection(uid);
                return false;
            }
        }
        return false;
    }

    private static void sendInitMsg(SseEmitter emitter, String uid) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("type", "connect");
            map.put("data", "success");
            map.put("timestamp", System.currentTimeMillis());

            emitter.send(SseEmitter.event()
                    .id(UUID.randomUUID().toString())
                    .data(JSON.toJSONString(map))
                    .reconnectTime(1000L));

            logger.debug("初始化消息发送成功, uid: {}", uid);
        } catch (IOException e) {
            logger.debug("发送初始化消息失败，uid = {}, error = {}", uid, e.getMessage());
        }
    }

    private static void sendMsg(SseEmitter emitter, Object obj, String uid) {
        try {
            emitter.send(SseEmitter.event()
                    .id(UUID.randomUUID().toString())
                    .data(JSON.toJSONString(obj))
                    .reconnectTime(1000L));
        } catch (Exception e) {
            logger.debug("发送消息失败，uid = {}, error = {}", uid, e.getMessage());
        }
    }

    /**
     * 获取统计信息
     */
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalConnections", SSE_CACHE.size());
        stats.put("totalTenants", SSE_TENANT_CACHE.size());
        stats.put("activeHeartbeats", HEARTBEAT_TASKS.size());

        Map<String, Integer> tenantStats = new HashMap<>();
        SSE_TENANT_CACHE.forEach((tenantId, userSet) -> {
            tenantStats.put(tenantId, userSet.size());
        });
        stats.put("tenantStats", tenantStats);

        return stats;
    }
}
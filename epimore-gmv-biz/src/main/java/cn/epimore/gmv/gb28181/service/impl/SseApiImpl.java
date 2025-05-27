package cn.epimore.gmv.gb28181.service.impl;

import cn.epimore.gmv.gb28181.service.api.SseApi;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SseApiImpl implements SseApi {
    private final static Logger logger = LoggerFactory.getLogger(SseApiImpl.class);
    private static final Map<String, SseEmitter> SSE_CACHE = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public SseEmitter connect(String uid) {
        SseEmitter sseEmitter = SSE_CACHE.computeIfAbsent(uid, key -> {
            SseEmitter emitter = getSseEmitter(uid);
            // 注册异常回调，调用 emitter.completeWithError() 触发
            emitter.onError(throwable -> {
                logger.error("连接异常，关闭，uid = {},{}", uid, throwable.getMessage());
                SSE_CACHE.remove(uid);
            });

            // 启动心跳机制
            startHeartbeat(emitter, uid);

            return emitter;
        });
        scheduler.schedule(() -> sendInitMsg(sseEmitter, uid), 1, TimeUnit.SECONDS);
        return sseEmitter;
    }

    @Override
    public void close(String uid) {
        SseEmitter sseEmitter = SSE_CACHE.remove(uid);
        if (sseEmitter != null) {
            sseEmitter.complete();
        }
    }

    @Override
    public void sendMsg(String uid, Object obj) {
        if (StringUtils.isEmpty(uid)) {
            SSE_CACHE.forEach((key, emitter) -> sendMsg(emitter, obj, key));
        } else {
            SseEmitter emitter = SSE_CACHE.get(uid);
            if (emitter != null) {
                sendMsg(emitter, obj, uid);
            }
        }
    }

    @Override
    public void sendDeviceMsg(String uid, Object msg) {
        Map<Object, Object> map = new HashMap<>();
        map.put("type", "device");
        map.put("data", msg);
        this.sendMsg(uid, map);
    }

    @Override
    public void sendNotifyMsg(String uid, Object msg) {
        Map<Object, Object> map = new HashMap<>();
        map.put("type", "notify");
        map.put("data", msg);
        this.sendMsg(uid, map);
    }

    private static SseEmitter getSseEmitter(String uid) {
        SseEmitter emitter = new SseEmitter(600_000L);
        // 设置响应头禁止缓存
        emitter.onTimeout(() -> {
            SSE_CACHE.remove(uid);
            logger.info("连接已超时，关闭，uid = {}", uid);
        });
        // 注册完成回调，调用 emitter.complete() 触发
        emitter.onCompletion(() -> {
            SSE_CACHE.remove(uid);
            logger.info("连接已释放，uid = {}", uid);
        });
        emitter.onError(err -> {
            logger.info("emitter，uid = {},err={}", uid, err.getMessage());
        });
        return emitter;
    }

    private static void sendInitMsg(SseEmitter emitter, String uid) {
        try {
            Map<Object, Object> map = new HashMap<>();
            map.put("type", "connect");
            map.put("data", "success");
            emitter.send(SseEmitter.event().reconnectTime(1000L).data(JSON.toJSONString(map)));
        } catch (IOException e) {
            emitter.complete();
            logger.error("发送初始化消息失败，uid = {},{}", uid, e.getMessage());
        }
    }

    private static void sendMsg(SseEmitter emitter, Object obj, String uid) {
        try {
            emitter.send(SseEmitter.event().reconnectTime(1000L).data(JSON.toJSONString(obj)));
        } catch (Exception e) {
            emitter.complete();
            SSE_CACHE.remove(uid); // 直接移除失效的 emitter
            logger.error("发送消息失败，uid = {},{}", uid, e.getMessage());
        }
    }

    private void startHeartbeat(SseEmitter emitter, String uid) {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                Map<Object, Object> map = new HashMap<>();
                map.put("type", "heartbeat");
                map.put("data", "💓");
                emitter.send(SseEmitter.event().reconnectTime(1000L).data(JSON.toJSONString(map)));
            } catch (IOException e) {
                logger.error("心跳发送失败，uid = {},{}", uid, e.getMessage());
                emitter.complete();
                SSE_CACHE.remove(uid);
            }
        }, 30, 30, TimeUnit.SECONDS); // 每30秒发送一次心跳
    }
}
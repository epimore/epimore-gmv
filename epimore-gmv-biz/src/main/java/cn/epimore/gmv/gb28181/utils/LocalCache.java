package cn.epimore.gmv.gb28181.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 简单的本地缓存工具类（带过期时间）
 */
public class LocalCache<K, V> {

    // 缓存对象，key -> ValueWrapper(value, expireTime)
    private final ConcurrentHashMap<K, ValueWrapper<V>> cache = new ConcurrentHashMap<>();

    // 定时清理过期缓存的线程池
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    public LocalCache() {
        // 每1分钟执行一次清理任务
        cleaner.scheduleAtFixedRate(this::cleanup, 1, 1, TimeUnit.MINUTES);
    }

    /**
     * 存入缓存
     * @param key 键
     * @param value 值
     * @param ttlMillis 过期时间（毫秒），如果是0或负数，则永不过期
     */
    public void put(K key, V value, long ttlMillis) {
        long expireAt = ttlMillis > 0 ? System.currentTimeMillis() + ttlMillis : -1L;
        cache.put(key, new ValueWrapper<>(value, expireAt));
    }

    /**
     * 取缓存
     */
    public V get(K key) {
        ValueWrapper<V> wrapper = cache.get(key);
        if (wrapper == null) {
            return null;
        }
        if (wrapper.isExpired()) {
            cache.remove(key);
            return null;
        }
        return wrapper.value;
    }

    /**
     * 移除缓存
     */
    public void remove(K key) {
        cache.remove(key);
    }

    /**
     * 清理所有缓存
     */
    public void clear() {
        cache.clear();
    }

    /**
     * 定时清理过期数据
     */
    private void cleanup() {
        long now = System.currentTimeMillis();
        for (Map.Entry<K, ValueWrapper<V>> entry : cache.entrySet()) {
            if (entry.getValue().isExpired(now)) {
                cache.remove(entry.getKey());
            }
        }
    }

    /**
     * 关闭清理线程
     */
    public void shutdown() {
        cleaner.shutdown();
    }

    /**
     * 内部包装类
     */
    private static class ValueWrapper<V> {
        private final V value;
        private final long expireAt; // 到期时间点

        ValueWrapper(V value, long expireAt) {
            this.value = value;
            this.expireAt = expireAt;
        }

        boolean isExpired() {
            return isExpired(System.currentTimeMillis());
        }

        boolean isExpired(long now) {
            return expireAt > 0 && now > expireAt;
        }
    }
}


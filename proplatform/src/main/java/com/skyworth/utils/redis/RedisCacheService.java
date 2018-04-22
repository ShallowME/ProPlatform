package com.skyworth.utils.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Redis缓存管理器
 */
public class RedisCacheService implements CacheManager,Destroyable{

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Resource
    private RedisCacheDao redisCacheDao;

    /**
     * 获取缓存实例
     * @param name
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {

        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache cache = caches.computeIfAbsent(name, k -> new RedisCacheDao<K, V>());
        return cache;
    }

    @Override
    public void destroy() throws Exception {
        redisCacheDao.destroy();
    }
}




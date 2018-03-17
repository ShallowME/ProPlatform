package com.skyworth.utils.redis;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.serial.SerialException;
import java.util.*;

/**
 * Created by Shallow on 2018/3/16.
 */
public class RedisCacheDao<K,V> implements Cache<K,V> {

    private final static Logger logger = LoggerFactory.getLogger(RedisCacheDao.class);

    private StringSerializer keySerializer = new StringSerializer();
    private ObjectSerializer valueSerializer = new ObjectSerializer();
    private IRedisManager redisManager;
    private Class<V> valueClass;

    @Getter
    @Setter
    private String keyPrefix = "";
    @Getter
    @Setter
    private int expire = 0;

    public RedisCacheDao() {
    }

    public RedisCacheDao(String keyPrefix, int expire) {
        this.keyPrefix = keyPrefix;
        this.expire = expire;
    }

    @Override
    public V get(K key) throws CacheException {
        logger.debug("get key [" + key + "]");

        if (key == null){
            logger.error("Key can not be null");
            return null;
        }

        try{
            String cacheKey = keyPrefix + key;
            byte[] rawValue = redisManager.get(keySerializer.serialize(cacheKey));
            if(rawValue == null){
                return null;
            }
            V value = (V)valueSerializer.deserialize(rawValue,valueClass);
            return value;
        } catch (SerialException e) {
            logger.error("Seriaizer error",e);
        }
        return null;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.debug("put key [" + key + "]");

        if (key == null){
            logger.error("Key can not be null");
            return null;
        }

        try{
            String cacheKey = keyPrefix + key;
            redisManager.set(keySerializer.serialize(cacheKey),
                    value != null ? valueSerializer.serialize(value, valueClass):null,
                    expire);
            return value;
        } catch (SerialException e) {
            logger.error("Seriaizer error",e);
        }
        return null;
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.debug("remove key [" + key + "]");

        if (key == null){
            return null;
        }

        try{
            String cacheKey = keyPrefix + key;
            byte[] rawValue = redisManager.get(keySerializer.serialize(cacheKey));
            V  previous = valueSerializer.deserialize(rawValue,valueClass);
            return previous;
        } catch (SerialException e) {
            logger.error("Seriaizer error",e);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("clear all cache");

        Set<byte[]> keys = null;
        try {
            keys = redisManager.keys(keySerializer.serialize(this.keyPrefix));
        }catch (SerialException e){
            logger.error("Serializer error");
        }
        if (keys == null || keys.size() == 0){
            return;
        }
        for (byte[] key:keys){
            redisManager.del(key);
        }
    }

    @Override
    public int size() {
        Long longSize = redisManager.dbSize();
        return longSize.intValue();
    }

    @Override
    public Set<K> keys() {
        Set<byte[]> keys = null;

        try{
            keys = redisManager.keys(keySerializer.serialize(this.keyPrefix));
        } catch (SerialException e) {
            logger.error("Serializer error",e);
        }

        if (CollectionUtils.isEmpty(keys)){
            return Collections.emptySet();
        }

        Set<K> convertKeys = null;
        for (byte[] key:keys){
            try {
                convertKeys.add((K) keySerializer.deserialize(key));
            } catch (SerialException e) {
                logger.error("Serializer error",e);
            }
        }
        return convertKeys;
    }

    @Override
    public Collection<V> values() {
        Set<byte[]> keys = null;

        try{
            keys = redisManager.keys(keySerializer.serialize(this.keyPrefix));
        } catch (SerialException e) {
            logger.error("Serializer error",e);
        }

        if (CollectionUtils.isEmpty(keys)){
            return Collections.emptyList();
        }

        List<V> values = new ArrayList<>();
        for (byte[] key:keys){
            V value = null;
            try {
                value = (valueSerializer.deserialize(redisManager.get(key),valueClass));
            } catch (SerialException e) {
                logger.error("Serializer error",e);
            }
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }
}

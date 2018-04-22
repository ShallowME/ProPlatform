package com.skyworth.utils.redis;

import com.skyworth.utils.ObjectSerializer;
import com.skyworth.utils.SerializeUtil;
import com.skyworth.utils.StringSerializer;
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
    private IRedisManager redisManager = new BaseRedisManager();
    private Class<V> valueClass = null;

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
        byte[] rawValue = new byte[0];
        if (key == null){
            logger.error("Key can not be null");
            return null;
        }

        try{
            String cacheKey = keyPrefix + key;
            rawValue = redisManager.get(SerializeUtil.serialize(cacheKey));
            if(rawValue == null){
                return null;
            }
        } catch (Exception e) {
            logger.error("Seriaizer error",e);
        }
        return (V)SerializeUtil.deserialize(rawValue,valueClass);

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
            redisManager.set(SerializeUtil.serialize(cacheKey),
                    value != null ? SerializeUtil.serialize(value):null,
                    expire);
            return value;
        } catch (Exception e) {
            logger.error("Seriaizer error",e);
        }
        return null;
    }

    @Override
    @SuppressWarnings({"unchecked", "varargs"})
    public V remove(K key) throws CacheException {
        logger.debug("remove key [" + key + "]");
        byte[] rawValue = null;

        if (key == null){
            return null;
        }

        try{
            String cacheKey = keyPrefix + key;
            rawValue = redisManager.get(SerializeUtil.serialize(cacheKey));
        } catch (Exception e) {
            logger.error("Seriaizer error",e);
        }
        return SerializeUtil.deserialize(rawValue,valueClass);
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("clear all cache");

        Set<byte[]> keys = null;
        try {
            keys = redisManager.keys(SerializeUtil.serialize(this.keyPrefix));
        }catch (Exception e){
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
    @SuppressWarnings({"unchecked", "varargs"})
    public Set<K> keys() {
        Set<byte[]> keys = null;

        try{
            keys = redisManager.keys(SerializeUtil.serialize(this.keyPrefix));
        } catch (Exception e) {
            logger.error("Serializer error",e);
        }

        if (CollectionUtils.isEmpty(keys)){
            return Collections.emptySet();
        }

        Set<K> convertKeys = null;
        for (byte[] key:keys){
            try {
                convertKeys.add((K) SerializeUtil.deserialize(key,String.class));
            } catch (Exception e) {
                logger.error("Serializer error",e);
            }
        }
        return convertKeys;
    }

    @Override
    @SuppressWarnings({"unchecked", "varargs"})
    public Collection<V> values() {
        Set<byte[]> keys = null;

        try{
            keys = redisManager.keys(SerializeUtil.serialize(this.keyPrefix));
        } catch (Exception e) {
            logger.error("Serializer error",e);
        }

        if (CollectionUtils.isEmpty(keys)){
            return Collections.emptyList();
        }

        List<V> values = new ArrayList<>();
        for (byte[] key:keys){
            V value = null;
            try {
                value = (SerializeUtil.deserialize(redisManager.get(key),valueClass));
            } catch (Exception e) {
                logger.error("Serializer error",e);
            }
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }

    public void destroy(){
        redisManager.destroy();
    }
}

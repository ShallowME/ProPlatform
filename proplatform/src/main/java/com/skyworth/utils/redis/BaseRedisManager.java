package com.skyworth.utils.redis;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shallow on 2018/3/15.
 */
public class BaseRedisManager implements IRedisManager {

    protected static final int DEFAULT_EXPIRE = 3600;

    @Getter
    @Setter
    protected int expire = DEFAULT_EXPIRE;

    protected static final int DEFAULT_COUNT = 100;

    @Getter
    @Setter
    protected int count = DEFAULT_COUNT;

    private final static Logger logger = LoggerFactory.getLogger(BaseRedisManager.class);

    @Getter
    @Setter
    private JedisPool jedisPool;

    /**
     * 从redis中取值
     * @param key
     * @return
     */
    @Override
    public byte[] get(byte[] key) {
        if (key == null) {
            return null;
        }
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            try {
                value = jedis.get(key);
            }finally {
                jedis.close();
            }
        }catch (JedisConnectionException e){
            logger.error("无法连接Redis数据库");
        }
        return value;
    }

    /**
     * 往jedis中存储数据
     * @param key
     * @param value
     * @param expire
     * @return
     */
    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {

        if(key == null || expire <= 0){
            return null;
        }

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            try {
                jedis.set(key,value);
                jedis.expire(key,expire);
            }finally {
                jedis.close();
            }
        }catch (JedisConnectionException e){
            logger.error("Error connecting Redis");
        }
        return value;
    }

    /**
     * 删除redis中的数据
     * @param key
     */
    @Override
    public void del(byte[] key) {
        if (key == null) {
            return;
        }

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            try {
                jedis.del(key);
            }finally {
                jedis.close();
            }
        }catch (JedisConnectionException e){
            logger.error("Error connecting Redis");
        }
    }

    /**
     * 获取数据库存储量
     * @return
     */
    @Override
    public Long dbSize() {
        Long size = 0L;

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            try {
                size = jedis.dbSize();
                return size;
            }finally {
                jedis.close();
            }
        }catch (JedisConnectionException e){
            logger.error("Error connecting Redis");
        }
        return null;
    }

    /**
     * 模糊匹配 取得 key 列表
     * @param pattern
     * @return
     */
    @Override
    public Set<byte[]> keys(byte[] pattern) {

        Set<byte[]> keys = null;

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            try {
                keys = new HashSet<byte[]>();
                ScanParams scanParams = new ScanParams();
                scanParams.count(count);
                scanParams.match(pattern);
                byte[] cursor = ScanParams.SCAN_POINTER_START_BINARY;
                ScanResult<byte[]> result;
                do{
                    result = jedis.scan(cursor,scanParams);
                    keys.addAll(result.getResult());
                    cursor = result.getCursorAsBytes();
                }while (result.getStringCursor().compareTo(ScanParams.SCAN_POINTER_START)>0);
            }finally {
                jedis.close();
            }
        }catch (JedisConnectionException e){
            logger.error("Error connecting Redis");
        }

        return null;
    }
}

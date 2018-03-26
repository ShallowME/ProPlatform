package com.skyworth.utils.redis;

import java.util.Set;

/**
 * Created by Shallow on 2018/3/15.
 */
public interface IRedisManager {

    byte[] get(byte[] key);

    byte[] set(byte[] key, byte[] value, int expire);

    Long dbSize();

    void del(byte[] key);

    Set<byte[]> keys(byte[] pattern);

    void destroy();
}

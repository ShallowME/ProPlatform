package com.skyworth.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

/**
 * Created by Shallow on 2018/3/15.
 */
public class ObjectSerializer{

    public <V>byte[] serialize(V v,Class<V> clazz) throws SerialException {
        return ProtostuffIOUtil.toByteArray(v, RuntimeSchema.createFrom(clazz), LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    public <V>V deserialize(byte[] bytes,Class<V> clazz) throws SerialException {
        RuntimeSchema<V> runtimeSchema = RuntimeSchema.createFrom(clazz);
        V  v= runtimeSchema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes,v,runtimeSchema);
        return v;
    }
}

/**
 * 将list封装成Java对象
 * @param <T>
 */
class CacheEntry<T> {
    @Getter
    @Setter
    private List<T> objectList;

    CacheEntry(List<T> objectList) {
        this.objectList = objectList;
    }
}
package com.skyworth.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.serial.SerialException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Shallow on 2018/3/15.
 */
public class StringSerializer {

    private final static Logger logger = LoggerFactory.getLogger(StringSerializer.class);


    private static final String DEFAULT_CHARSET = "UTF-8";

    private String charset = DEFAULT_CHARSET;

    public byte[] serialize(String s) throws SerialException{
        try {
            return (s == null ? null : s.getBytes(charset));
        }catch (UnsupportedEncodingException e) {
            logger.error("serialize error, string=" + s);
        }
        return  null;
    }

    public String deserialize(byte[] bytes) throws SerialException{
        try {
            return (bytes == null ? null : new String(bytes,charset));
        } catch (UnsupportedEncodingException e) {
            logger.error("deserialize error");
        }
        return null;
    }
}

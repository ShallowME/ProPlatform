package com.skyworth.utils.redis;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Shallow on 2018/3/26.
 */
public class RedisShiroSessionRepository implements ShiroSessionRepository {

    private StringSerializer stringSerializer;

    private ObjectSerializer objectSerializer;

    private  IRedisManager redisManager;

    private static final Logger logger = LoggerFactory.getLogger(RedisShiroSessionRepository.class);

    @Resource
    @Getter
    @Setter
    private RedisCacheDao<String,Object> redisCacheDao;

    public static final String SESSION_PREFIX = "propaltform-session:*";

    @Override
    public void saveSession(Session session) {
        if(session == null && session.getId() == null){
            throw new NullPointerException("session is empty");
        }
        try {
            byte[] sessionKey = stringSerializer.serialize(buildRedisSessionKey(session.getId()));

            if(session.getAttribute(CustomSessionService.SESSION_STATUS) == null){
                SessionStatus sessionStatus = new SessionStatus();
                session.setAttribute(CustomSessionService.SESSION_STATUS,sessionStatus);
            }

            byte[] sessionValue = objectSerializer.serialize(session,Session.class);

            redisManager.set(sessionKey,sessionValue, (int) (session.getTimeout()/1000));
        } catch (SerialException e) {
            logger.error("Serializer error"+e+" "+ buildRedisSessionKey(session.getId()));
        }


    }

    @Override
    public void deleteSession(Serializable sessionId) {
        if(sessionId == null){
            throw new  NullPointerException("session id is empty");
        }

        try{
            redisManager.del(stringSerializer.serialize(buildRedisSessionKey(sessionId)));
        } catch (SerialException e) {
            logger.error("Serializer error"+e+" "+ buildRedisSessionKey(sessionId));
        }
    }

    @Override
    public Session getSession(Serializable sessionId) {
        if(sessionId == null){
            throw new  NullPointerException("session id is empty");
        }

        Session session = null;
        try {
            byte[]  sessionValue = redisManager.get(stringSerializer.serialize(buildRedisSessionKey(sessionId)));
            session = objectSerializer.deserialize(sessionValue,Session.class);
            return session;
        } catch (SerialException e) {
            logger.error("Serializer error"+e+" "+ buildRedisSessionKey(sessionId));
        }
        return null;
    }

    @Override
    public Collection<Session> getAllSessions() {
        Set<Session> sessions = null;
        Set<byte[]> byteKeys = null;
        try {
            byteKeys = redisManager.keys(stringSerializer.serialize(SESSION_PREFIX));

            if (byteKeys != null && byteKeys.size()>0 ){
                for (byte[] sessionKey:byteKeys){
                    Session session = objectSerializer.deserialize(redisManager.get(sessionKey),Session.class);
                    sessions.add(session);
                }
            }
            return  sessions;
        } catch (SerialException e) {
            logger.error("Serializer error"+e);
        }
        return null;
    }

    private String buildRedisSessionKey(Serializable sessionId){
        return SESSION_PREFIX + sessionId;
    }

}

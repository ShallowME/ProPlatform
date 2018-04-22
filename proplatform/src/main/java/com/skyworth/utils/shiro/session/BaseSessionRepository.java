package com.skyworth.utils.shiro.session;

import com.skyworth.utils.ObjectSerializer;
import com.skyworth.utils.SerializeUtil;
import com.skyworth.utils.StringSerializer;
import com.skyworth.utils.redis.BaseRedisManager;
import com.skyworth.utils.redis.IRedisManager;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Shallow on 2018/3/26.
 */
public class BaseSessionRepository implements ShiroSessionRepository {

    public static final String SESSION_STATUS = "Online-Status";

    @Resource
    private IRedisManager redisManager = new BaseRedisManager();

    private static final Logger logger = LoggerFactory.getLogger(BaseSessionRepository.class);

    @Override
    public void saveSession(Session session,String prefix) {
        if(session == null || session.getId() == null){
            throw new NullPointerException("session is empty");
        }
        try {
            byte[] sessionKey = SerializeUtil.serialize(buildRedisSessionKey(session.getId(), prefix));

            if(session.getAttribute(SESSION_STATUS) == null){
                SessionStatus sessionStatus = new SessionStatus();
                session.setAttribute(SESSION_STATUS,sessionStatus);
            }

            byte[] sessionValue = SerializeUtil.serialize(session);

            redisManager.set(sessionKey,sessionValue, (int) (session.getTimeout()/1000));
        } catch (Exception e) {
            logger.error("Serializer error"+e+" "+ buildRedisSessionKey(session.getId(), prefix));
        }


    }

    @Override
    public void deleteSession(Serializable sessionId, String prefix) {
        if(sessionId == null){
            throw new  NullPointerException("session id is empty");
        }

        try{
            redisManager.del(SerializeUtil.serialize(buildRedisSessionKey(sessionId, prefix)));
        } catch (Exception e) {
            logger.error("Serializer error"+e+" "+ buildRedisSessionKey(sessionId, prefix));
        }
    }

    @Override
    public Session getSession(Serializable sessionId, String prefix) {
        if(sessionId == null){
            throw new  NullPointerException("session id is empty");
        }

        Session session;
        try {
            byte[]  sessionValue = redisManager.get(SerializeUtil.serialize(buildRedisSessionKey(sessionId, prefix)));
            session = SerializeUtil.deserialize(sessionValue,Session.class);
            return session;
        } catch (Exception e) {
            logger.error("Serializer error"+e+" "+ buildRedisSessionKey(sessionId, prefix));
        }
        return null;
    }

    @Override
    public Collection<Session> getAllSessions(String prefix) {
        Set<Session> sessions = null;
        Set<byte[]> byteKeys;
        try {
            byteKeys = redisManager.keys(SerializeUtil.serialize(prefix));
            if (byteKeys != null && byteKeys.size()>0 ){
                for (byte[] sessionKey:byteKeys){
                    Session session = SerializeUtil.deserialize(redisManager.get(sessionKey),Session.class);
                    if (session != null) {
                        sessions.add(session);
                    }
                }
            }
            return  sessions;
        } catch (Exception e) {
            logger.error("Serializer error"+e);
        }
        return null;
    }

    private String buildRedisSessionKey(Serializable sessionId, String prefix){
        return prefix + sessionId;
    }

}

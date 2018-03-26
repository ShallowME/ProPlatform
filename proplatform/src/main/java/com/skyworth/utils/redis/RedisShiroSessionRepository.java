package com.skyworth.utils.redis;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.Session;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Shallow on 2018/3/26.
 */
public class RedisShiroSessionRepository implements ShiroSessionRepository {

    @Resource
    @Getter
    @Setter
    private RedisCacheDao<String,Object> redisCacheDao;

    public static final String SESSION_PREFIX = "propaltform-sessioin:*";

    @Override
    public void saveSession(Session session) {
        if(session == null && session.getId() == null){
            throw new NullPointerException("session is empty");
        }


    }

    @Override
    public void deleteSession(Session session) {

    }

    @Override
    public Session getSession(Serializable sessionId) {
        return null;
    }

    @Override
    public Collection<Session> getAllSessions() {
        return null;
    }

    private String buildRedisSessionKey(Serializable sessionId){
        return SESSION_PREFIX + sessionId;
    }

}

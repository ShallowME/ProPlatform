package com.skyworth.utils.redis;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Shallow on 2018/3/26.
 */
public interface ShiroSessionRepository {

    void saveSession(Session session);

    void deleteSession(Session session);

    Session getSession(Serializable sessionId);

    Collection<Session> getAllSessions();
}

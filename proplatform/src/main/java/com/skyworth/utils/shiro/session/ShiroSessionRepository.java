package com.skyworth.utils.shiro.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Shallow on 2018/3/26.
 */
public interface ShiroSessionRepository {

    void saveSession(Session session, String prefix);

    void deleteSession(Serializable sessionId, String prefix);

    Session getSession(Serializable sessionId, String prefix);

    Collection<Session> getAllSessions(String prefix);
}

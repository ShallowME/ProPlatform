package com.skyworth.utils.shiro.session;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Shallow on 2018/3/15.
 */
public class CustomSessionDAO extends AbstractSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger(CustomSessionDAO.class);

    @Resource
    private ShiroSessionRepository shiroSessionRepository;

    @Getter
    @Setter
    private String prefix;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return shiroSessionRepository.getSession(sessionId, prefix);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        shiroSessionRepository.saveSession(session, prefix);
    }

    @Override
    public void delete(Session session) {
        if (session == null){
            logger.error("session cannot not be null");
        }else {
            Serializable sessionId = session.getId();
            shiroSessionRepository.deleteSession(sessionId, prefix);
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return shiroSessionRepository.getAllSessions(prefix);
    }
}

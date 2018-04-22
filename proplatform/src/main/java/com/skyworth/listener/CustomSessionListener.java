package com.skyworth.listener;

import com.skyworth.model.Company;
import com.skyworth.model.User;
import com.skyworth.utils.shiro.session.BaseSessionRepository;
import com.skyworth.utils.shiro.session.CompanySessionService;
import com.skyworth.utils.shiro.session.ShiroSessionRepository;
import com.skyworth.utils.shiro.session.UserSessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.SessionListenerAdapter;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by Shallow on 2018/4/2.
 */

public class CustomSessionListener implements SessionListener{

    public static final Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    private ShiroSessionRepository shiroSessionRepository = new BaseSessionRepository();

    @Override
    public void onStart(Session session) {
        logger.debug("on start");
    }

    @Override
    public void onStop(Session session) {
        logger.debug("on stop");
    }

    @Override
    public void onExpiration(Session session) {

        if (session == null){
            logger.error("Session can not be null.");
        }

        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (obj == null){
            throw new NullPointerException("Subject can not be null.");
        }
        if (obj instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            obj = spc.getPrimaryPrincipal();

            if (obj instanceof User)
                shiroSessionRepository.deleteSession(session.getId(), UserSessionService.USER_SESSION_PREFIX);
            else if (obj instanceof Company){
                shiroSessionRepository.deleteSession(session.getId(), CompanySessionService.COMPANY_SESSION_PREFIX);
            }
        }

    }
}

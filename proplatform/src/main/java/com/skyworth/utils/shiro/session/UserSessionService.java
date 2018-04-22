package com.skyworth.utils.shiro.session;

import com.skyworth.dto.UserOnlineDto;
import com.skyworth.model.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Shallow on 2018/3/29.
 */
public class UserSessionService {

    private  static final Logger logger = LoggerFactory.getLogger(UserSessionService.class);

    public static final String USER_SESSION_PREFIX = "proplatform-user-session:*";

    private static final String USER_SESSION_STATE = "user-online-state" ;

    @Resource
    private CustomSessionDAO customSessionDAO;

    public List<UserOnlineDto> getAllActiveUser(){
        customSessionDAO.setPrefix(USER_SESSION_PREFIX);
        Collection<Session> sessions = customSessionDAO.getActiveSessions();
        List<UserOnlineDto> uodolist = new ArrayList<>();

        sessions.forEach(session -> {
            UserOnlineDto uodo = getSessionDto(session);
            if (uodo != null){
                uodolist.add(uodo);
            }
        });

        return uodolist;
    }

    public List<SimplePrincipalCollection> getSimplePrincipleCollectionByUserId(Integer...userIds){
        Set<Integer> idset = new TreeSet<>();
        for (Integer userId: userIds){
            if (userId != null){
                idset.add(userId);
            }
        }

        customSessionDAO.setPrefix(USER_SESSION_PREFIX);
        Collection<Session> sessions = customSessionDAO.getActiveSessions();

        List<SimplePrincipalCollection> spcList = new ArrayList<>();

        sessions.forEach(session -> {
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (obj != null && obj instanceof SimplePrincipalCollection){
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                obj = spc.getPrimaryPrincipal();
                if (obj != null && obj instanceof User){
                    User user = (User)obj;
                    if (user != null && idset.contains(user.getId())){
                        spcList.add(spc);
                    }
                }
            }
        });
        return spcList;
    }



    private UserOnlineDto getSessionDto(Session session){
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (obj == null){
            return null;
        }

        if (obj instanceof SimplePrincipalCollection){
            SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
            obj = spc.getPrimaryPrincipal();

            if (obj != null){
                if (obj instanceof User){
                    UserOnlineDto userDto = new UserOnlineDto((User) obj);
                    userDto.setSessionId(session.getId().toString());
                    userDto.setLastAccess(session.getLastAccessTime());
                    userDto.setHost(session.getHost());
                    userDto.setTimeout(session.getTimeout());
                    userDto.setStartTime(session.getStartTimestamp());
                    SessionStatus sessionStatus = (SessionStatus)session.getAttribute(BaseSessionRepository.SESSION_STATUS);
                    boolean status = Boolean.TRUE;
                    if (sessionStatus != null){
                        status = sessionStatus.getOnlineState();
                    }
                    userDto.setSessionState(status);
                    return userDto;
                }
            }
        }
        return null;
    }

    public UserOnlineDto getUserSessoin(String sessionId){
        customSessionDAO.setPrefix(USER_SESSION_PREFIX);
        Session session = customSessionDAO.doReadSession(sessionId);
        UserOnlineDto uodo = getSessionDto(session);
        return uodo;
    }

    public Map<String, Object> changeSessionState(Boolean state, String sessionIds){
        Map<String, Object> map = new HashMap<>();
        try {
            String[] sessionIdsArray = null;
            if (!sessionIds.contains(",")){
                sessionIdsArray = new String[] {sessionIds};
            }else {
                sessionIdsArray = sessionIds.split(",");
            }

            for (String id: sessionIdsArray){
                customSessionDAO.setPrefix(USER_SESSION_PREFIX);
                Session session = customSessionDAO.doReadSession(id);
                SessionStatus sessionStatus = new SessionStatus();
                sessionStatus.setOnlineState(state);
                session.setAttribute(USER_SESSION_STATE, sessionStatus);
                customSessionDAO.update(session);
            }
            map.put("state",200);
            map.put("sessionState",state?1:0);
            map.put("sessionStateText",state?"激活":"踢出");
            map.put("sessionStateTextTd",state?"有效":"已踢出");
        }catch (Exception e){
            logger.error("Error changing session state"+e);
            map.put("state0",500);
            map.put("message","Change fail.The session may be null.Please try again.");
        }
        return map;
    }



}

package com.skyworth.utils.shiro.session;

import com.skyworth.dto.CompanyOnlineDto;
import com.skyworth.model.Company;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Shallow on 2018/4/20.
 */
public class CompanySessionService {

    private static final Logger logger = LoggerFactory.getLogger(UserSessionService.class);

    private static final String COMPANY_SESSION_STATE = "company-online-state";

    public static final String COMPANY_SESSION_PREFIX = "proplatform-session-prefix:*";

    @Resource
    private CustomSessionDAO customSessionDAO;

    public List<CompanyOnlineDto> getAllActiveCompany() {
        customSessionDAO.setPrefix(COMPANY_SESSION_PREFIX);
        Collection<Session> sessions = customSessionDAO.getActiveSessions();
        List<CompanyOnlineDto> codolist = new ArrayList<>();

        sessions.forEach(session -> {
            CompanyOnlineDto codo = getSessionDto(session);
            if (codo != null) {
                codolist.add(codo);
            }
        });

        return codolist;
    }

    private CompanyOnlineDto getSessionDto(Session session) {
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (obj == null) {
            return null;
        }

        if (obj instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            obj = spc.getPrimaryPrincipal();

            if (obj != null) {
                if (obj instanceof Company) {
                    CompanyOnlineDto companyDto = new CompanyOnlineDto((Company) obj);
                    companyDto.setSessionId(session.getId().toString());
                    companyDto.setLastAccess(session.getLastAccessTime());
                    companyDto.setHost(session.getHost());
                    companyDto.setTimeout(session.getTimeout());
                    companyDto.setStartTime(session.getStartTimestamp());
                    SessionStatus sessionStatus = (SessionStatus) session.getAttribute(BaseSessionRepository.SESSION_STATUS);
                    boolean status = Boolean.TRUE;
                    if (sessionStatus != null) {
                        status = sessionStatus.getOnlineState();
                    }
                    companyDto.setSessionState(status);
                    return companyDto;
                }
            }
        }
        return null;
    }

    public List<SimplePrincipalCollection> getSimplePrincipleCollectionByCompanyId(Integer...companyIds) {
        Set<Integer> idset = new TreeSet<>();
        for (Integer userId: companyIds){
            if (userId != null){
                idset.add(userId);
            }
        }
        customSessionDAO.setPrefix(COMPANY_SESSION_PREFIX);
        Collection<Session> sessions = customSessionDAO.getActiveSessions();
        List<SimplePrincipalCollection> spcList = new ArrayList<>();
        sessions.forEach(session -> {
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (obj != null && obj instanceof SimplePrincipalCollection){
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                obj = spc.getPrimaryPrincipal();
                if (obj != null && obj instanceof Company){
                    Company company = (Company)obj;
                    if (company != null && idset.contains(company.getId())){
                        spcList.add(spc);
                    }
                }
            }
        });
        return spcList;
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
                customSessionDAO.setPrefix(COMPANY_SESSION_PREFIX);
                Session session = customSessionDAO.doReadSession(id);
                SessionStatus sessionStatus = new SessionStatus();
                sessionStatus.setOnlineState(state);
                session.setAttribute(COMPANY_SESSION_STATE, sessionStatus);
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

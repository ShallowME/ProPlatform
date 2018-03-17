package com.skyworth.utils.shiro;

import com.skyworth.service.userService.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;


import javax.annotation.Resource;

/**
 * Created by Shallow on 2018/1/24.
 */
public class SecurityRealm extends AuthorizingRealm {

    private static String REALM_NAME = "securityRealm";

    @Resource
    private UserService userService;

    @Override
    public String getName(){
        return REALM_NAME;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String userName = (String) token.getPrincipal();
//        User userLogin = userService.findByUsername(userName);
//
//
//        if(userLogin == null){
//            throw new UnknownAccountException("账号不存在");
//        }
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                userLogin,
//                userLogin.getUserPassword(),
//                ByteSource.Util.bytes(userLogin.getUserSalt()),
//                getName()
//        );
//
//        return authenticationInfo;
        return null;
    }


    public void setSession(Object key,Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null){
            Session session = currentUser.getSession();
            System.out.print("session的默认超时时间为["+ session.getTimeout()+"]毫秒");
            if(session != null){
                session.setAttribute(key,value);
                session.setTimeout(3600000);
            }
        }
    }

}


package com.skyworth.utils.shiro;

import com.skyworth.model.Permission;
import com.skyworth.model.Role;
import com.skyworth.model.User;
import com.skyworth.service.userService.UserService;
import com.skyworth.utils.shiro.token.CustomizedToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shallow on 2018/1/24.
 */
public class UserSecurityRealm extends AuthorizingRealm {

    private static String REALM_NAME = "UserSecurityRealm";

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

        String identity = (String)principalCollection.getPrimaryPrincipal();
        User userLogin = userService.getByUsername(identity) == null?
                userService.getByPhoneNum(identity): userService.getByUsername(identity);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> rolesStr = new HashSet<>();
        Set<String> permissonsStr = new HashSet<>();
        Set<Role> roles = userService.getAllRoles(userLogin.getId());
        roles.forEach(r -> {
            rolesStr.add(r.getRoleName());
            Set<Permission> permissions = userService.getAllPermissions(r.getId());
            permissions.forEach(p -> permissonsStr.add(p.getPerName()));
        });
        authorizationInfo.setRoles(rolesStr);
        authorizationInfo.addStringPermissions(permissonsStr);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        SimpleAuthenticationInfo authenticationInfo = null;

        CustomizedToken customizedToken = (CustomizedToken)token;

        String identity = customizedToken.getUsername();

        if(userService.getByUsername(identity) == null && userService.getByPhoneNum(identity) == null){
            throw new UnknownAccountException("账号不存在");
        }

        else {
            User userLogin = userService.getByUsername(identity) == null?
                    userService.getByPhoneNum(identity): userService.getByUsername(identity);

           authenticationInfo = new SimpleAuthenticationInfo(
                    userLogin,
                    userLogin.getUserPassword(),
                    ByteSource.Util.bytes(userLogin.getUserSalt()),
                    getName()
            );

        }
        return authenticationInfo;
    }


//    public void setSession(Object key,Object value){
//        Subject currentUser = SecurityUtils.getSubject();
//        if(currentUser != null){
//            Session session = currentUser.getSession();
//            System.out.print("session的默认超时时间为["+ session.getTimeout()+"]毫秒");
//            if(session != null){
//                session.setAttribute(key,value);
//                session.setTimeout(3600000);
//            }
//        }
//    }

}


package com.skyworth.utils.shiro;

import com.skyworth.model.Company;
import com.skyworth.model.Permission;
import com.skyworth.model.Role;
import com.skyworth.service.companyService.CompanyService;
import com.skyworth.utils.shiro.token.CustomizedToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shallow on 2018/4/14.
 */
public class CompanySecurityRealm extends AuthorizingRealm {

    private static final String REALM_NAME = "CompanySecurityRealm";

    @Autowired
    private CompanyService companyService;

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

        String identity = (String) principalCollection.getPrimaryPrincipal();
        Company companyLogin = companyService.getByCompanyName(identity) == null?
                companyService.getByPhoneNum(identity):
                companyService.getByCompanyName(identity);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> rolesStr = new HashSet<>();
        Set<String> permissionsStr = new HashSet<>();
        Set<Role> roles = companyService.getAllRoles(companyLogin.getId());
        if (roles != null) {
            roles.forEach(r -> {
                rolesStr.add(r.getRoleName());
                Set<Permission> permissions = companyService.getAllPermissions(r.getId());
                permissions.forEach(p -> permissionsStr.add(p.getPerName()));
            });
            authorizationInfo.setRoles(rolesStr);
            authorizationInfo.addStringPermissions(permissionsStr);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        SimpleAuthenticationInfo authenticationInfo = null;

        CustomizedToken customizedToken = (CustomizedToken) authenticationToken;

        String identity = customizedToken.getUsername();

        if (companyService.getByCompanyName(identity) == null && companyService.getByPhoneNum(identity) == null){
            throw new UnknownAccountException("账号不存在");
        }
        else{
            Company companyLogin = companyService.getByCompanyName(identity) == null?
                    companyService.getByPhoneNum(identity):
                    companyService.getByCompanyName(identity);

            authenticationInfo = new SimpleAuthenticationInfo(
                    companyLogin,
                    companyLogin.getCompanyPassword(),
                    ByteSource.Util.bytes(companyLogin.getCompanyPassword()),
                    getName()
            );
        }
        return authenticationInfo;
    }
}

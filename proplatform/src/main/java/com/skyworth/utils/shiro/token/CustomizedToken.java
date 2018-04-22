package com.skyworth.utils.shiro.token;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Shallow on 2018/4/9.
 */
public class CustomizedToken extends UsernamePasswordToken {

    @Getter
    @Setter
    private String loginType;

    public CustomizedToken(final String identity, final String password, String loginType){
        super(identity,password);
        this.loginType = loginType;
    }
}

package com.skyworth.utils.shiro.token;

/**
 * Created by Shallow on 2018/4/9.
 */
public enum loginType {
    USER("User"), COMPANY("Company");

    private String type;

    private loginType(String type){
        this.type = type;
    }

}

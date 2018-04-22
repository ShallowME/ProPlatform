package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserInfo {
    private Integer userId;
    private String userUrl;
    private String userRealname;
    private String userSex;
    private String userProvince;
    private String userCity;
    private String userSchool;
    private String userMajor;
    private String userMailbox;
    private Date modificationTime;

    public UserInfo() {}

    public UserInfo(Integer userId, String userUrl, String userRealname, String userSex, String userProvince, String userCity, String userSchool, String userMajor, String userMailbox, Date modificationTime) {
        this.userId = userId;
        this.userUrl = userUrl;
        this.userRealname = userRealname;
        this.userSex = userSex;
        this.userProvince = userProvince;
        this.userCity = userCity;
        this.userSchool = userSchool;
        this.userMajor = userMajor;
        this.userMailbox = userMailbox;
        this.modificationTime = modificationTime;
    }
}

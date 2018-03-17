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
    private String userAddress;
    private String userSchool;
    private String userMajor;
    private String userMailbox;
    private String userProfession;
    private Date modificationTime;

    public UserInfo() {}

    public UserInfo(Integer userId, String userUrl, String userRealname, String userSex, String userAddress, String userSchool, String userMajor, String userMailbox, String userProfession, Date userModificationTime) {
        this.userId = userId;
        this.userUrl = userUrl;
        this.userRealname = userRealname;
        this.userSex = userSex;
        this.userAddress = userAddress;
        this.userSchool = userSchool;
        this.userMajor = userMajor;
        this.userMailbox = userMailbox;
        this.userProfession = userProfession;
        this.modificationTime = userModificationTime;
    }
}

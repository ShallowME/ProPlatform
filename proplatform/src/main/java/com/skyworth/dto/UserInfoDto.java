package com.skyworth.dto;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserInfoDto implements Serializable {
    private Integer userId;
    private String imgUrl;
    private String realname;
    private String userSex;
    private String userProvince;
    private String userCity;
    private String userSchool;
    private String userMajor;
    private String userMailbox;
    private String phoneNum;

    public UserInfoDto() {}

    public UserInfoDto(Integer userId, String imgUrl, String realname, String userSex, String userProvince, String userCity
            , String userSchool, String userMajor, String userMailbox, String phoneNum) {
        this.userId = userId;
        this.imgUrl = imgUrl;
        this.realname = realname;
        this.userSex = userSex;
        this.userProvince = userProvince;
        this.userCity = userCity;
        this.userSchool = userSchool;
        this.userMajor = userMajor;
        this.userMailbox = userMailbox;
        this.phoneNum = phoneNum;
    }
}

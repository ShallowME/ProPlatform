package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;
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

}

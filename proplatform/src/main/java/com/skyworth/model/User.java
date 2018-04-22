package com.skyworth.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by Shallow on 2018/3/6.
 */

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User {
    private Integer id;
    private String userName;
    private String userPhoneNum;
    private String userPassword;
    private String userSalt;
    private Date modificationTime;

    public User() {}

    public User(String userName, String userPhoneNum, String userPassword, String userSalt, Date modificationTime) {
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userPassword = userPassword;
        this.userSalt = userSalt;
        this.modificationTime = modificationTime;
    }

    public User(Integer id, String userName, String userPhoneNum, String userPassword, String userSalt, Date modificationTime) {
        this.id = id;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userPassword = userPassword;
        this.userSalt = userSalt;
        this.modificationTime = modificationTime;
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
        this.userPhoneNum = user.getUserPhoneNum();
        this.userSalt = user.getUserSalt();
    }
}

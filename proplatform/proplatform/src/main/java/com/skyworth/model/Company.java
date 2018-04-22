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
public class Company {
    private Integer id;
    private String companyName;
    private String companyPhoneNum;
    private String companyPassword;
    private String companySalt;
    private Date modificationTime;

    public Company() {}

    public Company(String companyName, String companyPassword) {
        this.companyName = companyName;
        this.companyPassword = companyPassword;
    }

    public Company(Integer id, String companyName, String companyPhoneNum, String companyPassword, String companySalt) {
        this.id = id;
        this.companyName = companyName;
        this.companyPhoneNum = companyPhoneNum;
        this.companyPassword = companyPassword;
        this.companySalt = companySalt;
    }
}

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
public class Company implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String companyName;
    private String companyPhoneNum;
    private String companyPassword;
    private String companySalt;
    private Date modificationTime;


    public Company(String companyName, String companyPassword) {
        this.companyName = companyName;
        this.companyPassword = companyPassword;
    }


    public Company(Company company) {
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        this.companyPhoneNum = company.getCompanyPhoneNum();
        this.companyPassword = company.getCompanyPassword();
        this.companySalt = company.getCompanySalt();
    }
}

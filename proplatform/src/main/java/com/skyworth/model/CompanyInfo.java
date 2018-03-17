package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CompanyInfo {
    private Integer companyId;
    private String companyLogo;
    private String companyRealname;
    private String companyMajor;
    private String companyDescription;
    private Date modificationTime;

    public CompanyInfo() {
    }

    public CompanyInfo(String companyRealname, String companyMajor) {
        this.companyRealname = companyRealname;
        this.companyMajor = companyMajor;
    }

    public CompanyInfo(Integer companyId, String companyLogo, String companyRealname, String companyMajor, String companyDescription, Date modificationTime) {
        this.companyId = companyId;
        this.companyLogo = companyLogo;
        this.companyRealname = companyRealname;
        this.companyMajor = companyMajor;
        this.companyDescription = companyDescription;
        this.modificationTime = modificationTime;
    }
}

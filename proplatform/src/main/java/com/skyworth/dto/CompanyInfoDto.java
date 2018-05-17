package com.skyworth.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CompanyInfoDto {
    private String imgUrl;
    private String companyRealname;
    private Integer companyProCount;
    private String companyMajor;
    private String companyDescription;

    public CompanyInfoDto() {
    }

    public CompanyInfoDto(String imgUrl, String companyRealname, Integer companyProCount, String companyMajor, String companyDescription) {
        this.imgUrl = imgUrl;
        this.companyRealname = companyRealname;
        this.companyProCount = companyProCount;
        this.companyMajor = companyMajor;
        this.companyDescription = companyDescription;
    }
}

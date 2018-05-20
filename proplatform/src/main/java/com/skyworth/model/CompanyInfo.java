package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer companyId;
    private String companyLogo;
    private String companyRealname;
    private String companyLocation;
    private String companyMajor;
    private String companyDescription;
    private Date modificationTime;

    public CompanyInfo(String companyRealname, String companyMajor) {
        this.companyRealname = companyRealname;
        this.companyMajor = companyMajor;
    }

}

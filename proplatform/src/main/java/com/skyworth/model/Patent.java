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
public class Patent {
    private Integer id;
    private Integer userId;
    private String patentImg;
    private String patentName;
    private String patentOwner;
    private String patentCertiCode;
    private Long patentApplyDate;
    private Long patentAuthDate;
    private String patentContact;
    private Date modificationTime;

    public Patent(Integer id, Integer userId, String patentImg, String patentName, String patentOwner, String patentCertiCode, Long patentApplyDate, Long patentAuthDate, String patentContact, Date modificationTime) {
        this.id = id;
        this.userId = userId;
        this.patentImg = patentImg;
        this.patentName = patentName;
        this.patentOwner = patentOwner;
        this.patentCertiCode = patentCertiCode;
        this.patentApplyDate = patentApplyDate;
        this.patentAuthDate = patentAuthDate;
        this.patentContact = patentContact;
        this.modificationTime = modificationTime;
    }

    public Patent() {}
}

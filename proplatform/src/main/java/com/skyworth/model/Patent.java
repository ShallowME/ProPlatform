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
    private String patentNum;
    private Long patentApplyDate;
    private Long patentAuthDate;
    private String patentCertiCode;
    private String patentContact;
    private Date modificationTime;

    public Patent() {}

    public Patent(Integer id, Integer userId, String patentImg, String patentName, String patentOwner, String patentNum, Long patentApplyDate, Long patentAuthDate
            , String patentCertiCode, String patentContact, Date modificationTime) {
        this.id = id;
        this.userId = userId;
        this.patentImg = patentImg;
        this.patentName = patentName;
        this.patentOwner = patentOwner;
        this.patentNum = patentNum;
        this.patentApplyDate = patentApplyDate;
        this.patentAuthDate = patentAuthDate;
        this.patentCertiCode = patentCertiCode;
        this.patentContact = patentContact;
        this.modificationTime = modificationTime;
    }
}

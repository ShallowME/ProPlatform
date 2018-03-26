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
}

package com.skyworth.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PatentDto {

    private String imgUrl;
    private String patentName;
    private String patentOwner;
    private String patentNum;
    private Long patentApplyTime;
    private Long patentAuthTime;
    private String patentCertiCode;
    private String patentContact;

    public PatentDto() {}

    public PatentDto(String imgUrl, String patentName, String patentOwner, String patentNum, Long patentApplyTime
            , Long patentAuthTime, String patentCertiCode, String patentContact) {
        this.imgUrl = imgUrl;
        this.patentName = patentName;
        this.patentOwner = patentOwner;
        this.patentNum = patentNum;
        this.patentApplyTime = patentApplyTime;
        this.patentAuthTime = patentAuthTime;
        this.patentCertiCode = patentCertiCode;
        this.patentContact = patentContact;
    }
}

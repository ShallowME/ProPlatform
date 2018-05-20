package com.skyworth.dto;

import lombok.*;

import java.util.Date;

/**
 * Created by Shallow on 2018/5/13.
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PatentDto {
    private Integer patentId;
    private Integer userId;
    private String patentImg;
    private String patentName;
    private String patentOwner;
    private String patentNum;
    private Date patentApplyDate;
    private Date patentAuthDate;
    private String patentCertiCode;
    private String patentContact;
}

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
public class Patent implements Serializable{
    private static final long serialVersionUID = 1L;
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

}

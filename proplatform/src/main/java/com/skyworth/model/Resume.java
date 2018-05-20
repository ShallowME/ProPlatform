package com.skyworth.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Resume implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String resumeImg;
    private String resumeRealname;
    private String resumeSex;
    private String resumeBirth;
    private String resumeSchool;
    private String resumeEducation;
    private String resumeMajor;
    private String resumeProfessionType;
    private String resumeProfession;
    private String resumeProvince;
    private String resumeMailbox;
    private String resumePhoneNum;
    private String resumeSchExperience;
    private String resumeWorkExperience;
    private String modificationTime;

}

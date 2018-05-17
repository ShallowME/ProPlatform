package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Resume {
    private Integer id;
    private Integer userId;
    private String resumeImg;
    private String resumeRealname;
    private String resumeSex;
    private String resumeBirth;
    private String resumeSchool;
    private String resumeEducation;
    private String resumeMajor;
    private String resumeMailbox;
    private String resumePhonenum;
    private String resumeSchExperience;
    private String resumeWorkExperience;
    private String modificationTime;

    public Resume() {}

    public Resume(Integer id, Integer userId, String resumeImg, String resumeRealname, String resumeSex, String resumeBirth, String resumeSchool, String resumeEducation, String resumeMajor, String resumeMailbox, String resumePhonenum, String resumeSchExperience, String resumeWorkExperience, String modificationTime) {
        this.id = id;
        this.userId = userId;
        this.resumeImg = resumeImg;
        this.resumeRealname = resumeRealname;
        this.resumeSex = resumeSex;
        this.resumeBirth = resumeBirth;
        this.resumeSchool = resumeSchool;
        this.resumeEducation = resumeEducation;
        this.resumeMajor = resumeMajor;
        this.resumeMailbox = resumeMailbox;
        this.resumePhonenum = resumePhonenum;
        this.resumeSchExperience = resumeSchExperience;
        this.resumeWorkExperience = resumeWorkExperience;
        this.modificationTime = modificationTime;
    }
}


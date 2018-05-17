package com.skyworth.dto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResumeDto {

    private String ImgUrl;
    private String resumeRealname;
    private String resumeSex;
    private String resumeBirth;
    private String resumeSchool;
    private String resumeEducation;
    private String resumeMajor;
    private String resumeMailbox;
    private String resumePhoneNum;
    private String resumeSchExperience;
    private String resumeWorkExperience;

    public ResumeDto() {}

    public ResumeDto(String imgUrl, String resumeRealname, String resumeSex, String resumeBirth, String resumeSchool, String resumeEducation, String resumeMajor, String resumeMailbox, String resumePhoneNum, String resumeSchExperience, String resumeWorkExperience) {

        this.ImgUrl = imgUrl;
        this.resumeRealname = resumeRealname;
        this.resumeSex = resumeSex;
        this.resumeBirth = resumeBirth;
        this.resumeSchool = resumeSchool;
        this.resumeEducation = resumeEducation;
        this.resumeMajor = resumeMajor;
        this.resumeMailbox = resumeMailbox;
        this.resumePhoneNum = resumePhoneNum;
        this.resumeSchExperience = resumeSchExperience;
        this.resumeWorkExperience = resumeWorkExperience;
    }
}


package com.skyworth.dto;

import com.skyworth.model.Resume;
import lombok.*;

/**
 * Created by Shallow on 2018/5/13.
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Integer resumeId;
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

    private long resumeCompletedProjects;
    private int selectMark;

    public ResumeDto(Resume resume){
        this.resumeId = resume.getId();
        this.userId = resume.getUserId();
        this.resumeImg = resume.getResumeImg();
        this.resumeRealname = resume.getResumeRealname();
        this.resumeSex = resume.getResumeSex();
        this.resumeBirth = resume.getResumeBirth();
        this.resumeSchool = resume.getResumeSchool();
        this.resumeEducation = resume.getResumeEducation();
        this.resumeMajor = resume.getResumeMajor();
        this.resumeProfessionType = resume.getResumeProfessionType();
        this.resumeProfession = resume.getResumeProfession();
        this.resumeProvince = resume.getResumeProvince();
        this.resumeMailbox = resume.getResumeMailbox();
        this.resumePhoneNum = resume.getResumePhoneNum();
        this.resumeSchExperience = resume.getResumeSchExperience();
        this.resumeWorkExperience = resume.getResumeWorkExperience();
    }
}

package com.skyworth.dto;

import com.skyworth.model.Project;
import com.skyworth.utils.TimeFormatUtil;
import lombok.*;

import java.util.Date;

/**
 * Created by Shallow on 2018/5/11.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProjectDto {
    private int projectId;
    private Integer companyId;
    private String companyName;
    private String projectName;
    private Double projectMoney;
    private String projectType;
    private int projectCycle;
    private Date projectPubTime;
    private int projectEnrollment;
    private String projectDescription;
    private String projectRequest;
    private Integer projectState;

    public ProjectDto(Integer companyId, String companyName, String projectName, Double projectMoney, String projectType, int projectCycle, String projectDescription, String projectRequest, Integer projectState) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.projectName = projectName;
        this.projectMoney = projectMoney;
        this.projectType = projectType;
        this.projectCycle = projectCycle;
        this.projectDescription = projectDescription;
        this.projectRequest = projectRequest;
        this.projectState = projectState;
    }

    public ProjectDto(Project project){
        this.projectId = project.getId();
        this.companyId = project.getCompanyId();
        this.companyName = project.getCompanyName();
        this.projectName = project.getProName();
        this.projectMoney = project.getProMoney();
        this.projectType = project.getProType();
        this.projectCycle = project.getProCycle();
        this.projectPubTime = TimeFormatUtil.longToDate(project.getProPubTime());
        this.projectEnrollment = project.getProEnrollment();
        this.projectDescription = project.getProDescription();
        this.projectRequest = project.getProRequest();
    }
}

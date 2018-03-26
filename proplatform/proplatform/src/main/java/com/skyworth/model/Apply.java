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
public class Apply {
    private Integer id;
    private Integer applicantId;
    private Integer resumeId;
    private Integer companyId;
    private Integer proId;
    private Integer applyState;
    private Integer applyMark;
    private Date modificationTime;

    public Apply() {}

    public Apply(Integer applicantId, Integer proId) {
        this.applicantId = applicantId;
        this.proId = proId;
    }

    public Apply(Integer id, Integer applicantId, Integer resumeId, Integer companyId, Integer proId, Integer applyState, Integer applyMark, Date modificationTime) {
        this.id = id;
        this.applicantId = applicantId;
        this.resumeId = resumeId;
        this.companyId = companyId;
        this.proId = proId;
        this.applyState = applyState;
        this.applyMark = applyMark;
        this.modificationTime = modificationTime;
    }
}

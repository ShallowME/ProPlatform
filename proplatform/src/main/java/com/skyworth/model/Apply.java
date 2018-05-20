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
public class Apply implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer applicantId;
    private Integer resumeId;
    private Integer companyId;
    private Integer proId;
    private Integer applyState;
    private Integer applyMark;
    private Date modificationTime;

    public Apply(Integer applicantId, Integer proId) {
        this.applicantId = applicantId;
        this.proId = proId;
    }

    public Apply(Integer applicantId, Integer resumeId, Integer companyId, Integer proId) {
        this.applicantId = applicantId;
        this.resumeId = resumeId;
        this.companyId = companyId;
        this.proId = proId;
    }

}

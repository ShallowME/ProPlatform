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
public class Target {
    private Integer id;
    private Integer stageId;
    private Long targetDeadline;
    private String targetDetail;
    private String targetRemarks;
    private Date modificationTime;

    public Target() {}

    public Target(Integer id, Integer stageId, Long targetDeadline, String targetDetail, String targetRemarks, Date modificationTime) {
        this.id = id;
        this.stageId = stageId;
        this.targetDeadline = targetDeadline;
        this.targetDetail = targetDetail;
        this.targetRemarks = targetRemarks;
        this.modificationTime = modificationTime;
    }
}

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
public class Stage {
    private Integer id;
    private Integer proId;
    private Integer stageNum;
    private Long stageStartTime;
    private Long stageEndTime;
    private Long stageSettleTime;
    private Integer stageSpeed;
    private Date modificationTime;

    public Stage() {}

    public Stage(Integer id, Integer proId, Integer stageNum, Long stageStartTime, Long stageEndTime, Long stageSettleTime, Integer stageSpeed, Date modificationTime) {
        this.id = id;
        this.proId = proId;
        this.stageNum = stageNum;
        this.stageStartTime = stageStartTime;
        this.stageEndTime = stageEndTime;
        this.stageSettleTime = stageSettleTime;
        this.stageSpeed = stageSpeed;
        this.modificationTime = modificationTime;
    }
}

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
    private Long stageStarttime;
    private Long stageEndtime;
    private Long stageSettletime;
    private Integer stageSpeed;
    private Date modificationTime;

    public Stage() {}

    public Stage(Integer id, Integer proId, Integer stageNum, Long stageStarttime, Long stageEndtime, Long stageSettletime, Integer stageSpeed, Date modificationTime) {
        this.id = id;
        this.proId = proId;
        this.stageNum = stageNum;
        this.stageStarttime = stageStarttime;
        this.stageEndtime = stageEndtime;
        this.stageSettletime = stageSettletime;
        this.stageSpeed = stageSpeed;
        this.modificationTime = modificationTime;
    }
}

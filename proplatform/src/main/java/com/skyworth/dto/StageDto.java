package com.skyworth.dto;

import com.skyworth.model.Stage;
import com.skyworth.model.Target;
import com.skyworth.utils.TimeFormatUtil;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Shallow on 2018/5/18.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class StageDto {
    private Integer stageId;
    private Integer proId;
    private Integer stageNum;
    private Date stageStartTime;
    private Date stageEndTime;
    private String stageSettleRequest;
    private Float stageSpeed;
    private List<Target> targets;

    public StageDto(Stage stage){
        this.stageId = stage.getId();
        this.proId = stage.getProId();
        this.stageNum = stage.getStageNum();
        this.stageStartTime = TimeFormatUtil.longToDate(stage.getStageStartTime());
        this.stageEndTime = TimeFormatUtil.longToDate(stage.getStageEndTime());
    }
}

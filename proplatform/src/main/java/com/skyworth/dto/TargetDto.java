package com.skyworth.dto;

import com.skyworth.model.Target;
import com.skyworth.utils.TimeFormatUtil;
import lombok.*;

import java.io.File;
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
public class TargetDto {
    private Integer targetId;
    private Integer stageId;
    private Date targetDeadline;
    private String targetDetail;
    private Integer targetState;
    private List<File> files;

    public TargetDto(Target target){
        this.targetId = target.getId();
        this.targetDeadline = TimeFormatUtil.longToDate(target.getTargetDeadline());
        this.targetDetail = target.getTargetDetail();
        this.targetState = target.getTargetState();
    }
}

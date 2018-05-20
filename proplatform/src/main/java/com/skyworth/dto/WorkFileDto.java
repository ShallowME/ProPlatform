package com.skyworth.dto;

import com.skyworth.model.WorkFile;
import com.skyworth.utils.TimeFormatUtil;
import lombok.*;

import java.util.Date;

/**
 * Created by Shallow on 2018/5/20.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkFileDto {
    private Integer workFileId;
    private Integer targetId;
    private Integer stageNum;
    private Integer userId;
    private String workFileName;
    private Double workFileSize;
    private String workFileUploader;
    private Date workFileModifyDate;
    private String workFileUrl;

    public WorkFileDto(WorkFile workFile){
        this.workFileId = workFile.getId();
        this.targetId = workFile.getTargetId();
        this.userId = workFile.getUserId();
        this.workFileName = workFile.getWorkFileName();
        this.workFileSize = workFile.getWorkFileSize();
        this.workFileUploader = workFile.getWorkFileUploader();
        this.workFileModifyDate = TimeFormatUtil.longToDate(workFile.getWorkFileModifyDate());
        this.workFileUrl = workFile.getWorkFileUrl();
    }
}

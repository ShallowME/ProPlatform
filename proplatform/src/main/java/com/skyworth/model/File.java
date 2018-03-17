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
public class File {
    private Integer id;
    private Integer stageId;
    private Integer userId;
    private Integer companyId;
    private String fileName;
    private Double fileSize;
    private String fileUploader;
    private Long fileModifydate;
    private String fileUrl;
    private Date modificationTime;

    public File() {}

    public File(Integer id, Integer stageId, Integer userId, Integer companyId, String fileName, Double fileSize, String fileUploader, Long fileModifydate, String fileUrl, Date modificationTime) {
        this.id = id;
        this.stageId = stageId;
        this.userId = userId;
        this.companyId = companyId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileUploader = fileUploader;
        this.fileModifydate = fileModifydate;
        this.fileUrl = fileUrl;
        this.modificationTime = modificationTime;
    }
}

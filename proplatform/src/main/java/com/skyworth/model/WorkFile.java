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
public class WorkFile implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer targetId;
    private Integer userId;
    private String workFileName;
    private Double workFileSize;
    private String workFileUploader;
    private Long workFileModifyDate;
    private String workFileUrl;
    private Date modificationTime;

}

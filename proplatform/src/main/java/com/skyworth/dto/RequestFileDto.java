package com.skyworth.dto;

import lombok.*;

import java.util.Date;

/**
 * Created by Shallow on 2018/5/20.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RequestFileDto {
    private Integer requestFileId;
    private Integer companyId;
    private Integer proId;
    private String requestFileName;
    private Integer requestFileSize;
    private String requestFileUploader;
    private Date requestFileModifyDate;
    private String requestFileUrl;
}

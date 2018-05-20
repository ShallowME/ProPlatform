package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shallow on 2018/5/19.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RequestFile implements Serializable {
    private Integer id;
    private Integer companyId;
    private Integer proId;
    private String requestFileName;
    private Integer requestFileSize;
    private String requestFileUploader;
    private Long requestFileModifyDate;
    private String requestFileUrl;
    private Date modificationTime;
}

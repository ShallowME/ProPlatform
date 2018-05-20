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
public class Target implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer stageId;
    private Long targetDeadline;
    private String targetDetail;
    private Integer targetState;
    private Date modificationTime;

}

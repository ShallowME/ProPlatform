package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer companyId;
    private String mesContent;
    private Integer mesState;
    private Date modificationTime;

}

package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Message {
    private Integer id;
    private Integer userId;
    private Integer companyId;
    private String mesContent;
    private Integer mesState;
    private Date modificationTime;

    public Message() {
    }

    public Message(Integer id, Integer userId, Integer companyId, String mesContent, Integer mesState, Date modificationTime) {
        this.id = id;
        this.userId = userId;
        this.companyId = companyId;
        this.mesContent = mesContent;
        this.mesState = mesState;
        this.modificationTime = modificationTime;
    }
}

package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Permission {
    private Integer id;
    private String perName;
    private String perDescription;
    private Date modificationTime;

    public Permission() {}

    public Permission(String perName, String perDescription) {
        this.perName = perName;
        this.perDescription = perDescription;
    }

    public Permission(Integer id, String perName, String perDescription, Date modificationTime) {
        this.id = id;
        this.perName = perName;
        this.perDescription = perDescription;
        this.modificationTime = modificationTime;
    }
}

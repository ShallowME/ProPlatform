package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String perName;
    private String perDescription;
    private Date modificationTime;

    public Permission(String perName, String perDescription) {
        this.perName = perName;
        this.perDescription = perDescription;
    }
}

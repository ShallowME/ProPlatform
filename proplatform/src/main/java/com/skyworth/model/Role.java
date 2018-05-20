package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String roleName;
    private String roleDescription;
    private Date modificationTime;

}

package com.skyworth.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer roleId;
    private Integer permissionId;

}

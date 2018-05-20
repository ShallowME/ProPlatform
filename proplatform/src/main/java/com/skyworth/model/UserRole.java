package com.skyworth.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer roleId;
}

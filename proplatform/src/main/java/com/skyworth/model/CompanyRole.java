package com.skyworth.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRole implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer companyId;
    private Integer roleId;

}

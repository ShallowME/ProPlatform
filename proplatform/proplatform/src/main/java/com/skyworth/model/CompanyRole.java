package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CompanyRole {
    private Integer companyId;
    private Integer roleId;

    public CompanyRole() {}

    public CompanyRole(Integer companyId, Integer roleId) {
        this.companyId = companyId;
        this.roleId = roleId;
    }
}

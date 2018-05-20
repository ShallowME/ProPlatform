package com.skyworth.dto;

import lombok.*;

/**
 * Created by Shallow on 2018/5/5.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CompanyDto {

    private String companyName;
    private String phoneNum;
    private String password;
}

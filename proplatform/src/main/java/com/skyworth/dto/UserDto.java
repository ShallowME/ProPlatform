package com.skyworth.dto;

import lombok.*;

/**
 * Created by Shallow on 2018/4/27.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private String userName;
    private String phoneNum;
    private String password;
}

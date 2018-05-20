package com.skyworth.dto;

import lombok.*;

/**
 * Created by Shallow on 2018/5/14.
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDto {
    private Integer applyId;
    private Integer applicantId;
    private Integer resumeId;
    private Integer companyId;
    private Integer proId;
    private Integer applyState;
    private Integer applyMark;
}

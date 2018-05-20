package com.skyworth.dto;

import lombok.*;

/**
 * Created by Shallow on 2018/5/17.
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class InviteDto {
    private Integer inviteId;
    private Integer inviteeId;
    private Integer resumeId;
    private Integer companyId;
    private Integer proId;
    private Integer inviteState;
    private Integer inviteMark;

    public InviteDto(Integer resumeId, Integer companyId, Integer proId) {
        this.resumeId = resumeId;
        this.companyId = companyId;
        this.proId = proId;
    }
}

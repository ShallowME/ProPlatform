package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Invite {
    private Integer id;
    private Integer resumeId;
    private Integer companyId;
    private Integer proId;
    private Integer inviteState;
    private Integer inviteMark;
    private Date modificationTime;

    public Invite() {
    }

    public Invite(Integer id, Integer resumeId, Integer companyId, Integer proId, Integer inviteState, Integer inviteMark, Date modificationTime) {
        this.id = id;
        this.resumeId = resumeId;
        this.companyId = companyId;
        this.proId = proId;
        this.inviteState = inviteState;
        this.inviteMark = inviteMark;
        this.modificationTime = modificationTime;
    }
}

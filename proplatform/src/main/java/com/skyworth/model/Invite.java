package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Invite implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer inviteeId;
    private Integer resumeId;
    private Integer companyId;
    private Integer proId;
    private Integer inviteState;
    private Integer inviteMark;
    private Date modificationTime;

}

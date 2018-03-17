package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
/**
 * @author li
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Project {
    private Integer id;
    private Integer companyId;
    private String companyName;
    private String proName;
    private Double proMoney;
    private String proType;
    private Integer proCycle;
    private long proPubtime;
    private String proDescription;
    private String proRequest;
    private Integer proState;
    private Date modificationTime;

    public Project() {}

    public Project(Integer id, Integer companyId, String companyName, String proName, Double proMoney, String proType, Integer proCycle, long proPubtime, String proDescription, String proRequest, Integer proState, Date modificationTime) {
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
        this.proName = proName;
        this.proMoney = proMoney;
        this.proType = proType;
        this.proCycle = proCycle;
        this.proPubtime = proPubtime;
//        this.proEnrollment = proEnrollment;
        this.proDescription = proDescription;
        this.proRequest = proRequest;
        this.proState = proState;
        this.modificationTime = modificationTime;
    }

    public Project(String companyName, String proName) {
        this.companyName = companyName;
        this.proName = proName;
    }
}

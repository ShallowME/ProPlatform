package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
/**
 * @author li
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer companyId;
    private String companyName;
    private String proName;
    private Double proMoney;
    private String proType;
    private Integer proCycle;
    private long proPubTime;
    private int proEnrollment;
    private String proDescription;
    private String proRequest;
    private Integer proState;
    private Date modificationTime;

    public Project(String companyName, String proName) {
        this.companyName = companyName;
        this.proName = proName;
    }
}

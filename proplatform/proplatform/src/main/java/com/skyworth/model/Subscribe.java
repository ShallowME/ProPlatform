package com.skyworth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Subscribe {
    private Integer id;
    private Integer userId;
    private String subSpot;
    private String subType;
    private Integer subMaxPay;
    private Integer subMinPay;
    private Date modificationTime;

    public Subscribe() {}

    public Subscribe(Integer id, Integer userId, String subSpot, String subType, Integer subMaxPay, Integer subMinPay, Date modificationTime) {
        this.id = id;
        this.userId = userId;
        this.subSpot = subSpot;
        this.subType = subType;
        this.subMaxPay = subMaxPay;
        this.subMinPay = subMinPay;
        this.modificationTime = modificationTime;
    }
}

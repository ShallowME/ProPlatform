package com.skyworth.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Subscribe implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String subSpot;
    private String subType;
    private Integer subMaxPay;
    private Integer subMinPay;
    private Date modificationTime;
}

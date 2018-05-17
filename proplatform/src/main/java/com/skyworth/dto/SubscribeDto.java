package com.skyworth.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SubscribeDto {
    private String subscribeSpot;
    private String subscribeType;
    private Integer subscribeMaxPay;
    private Integer subscribeMinPay;

    public SubscribeDto() {}

    public SubscribeDto(String subscribeSpot, String subscribeType, Integer subscribeMaxPay, Integer subscribeMinPay) {
        this.subscribeSpot = subscribeSpot;
        this.subscribeType = subscribeType;
        this.subscribeMaxPay = subscribeMaxPay;
        this.subscribeMinPay = subscribeMinPay;
    }
}

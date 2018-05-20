package com.skyworth.utils;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Shallow on 2018/4/23.
 */


public class ConstantHolder {

    public static final String SERVER = "open.ucpaas.com";
    public static final String SID = "05f9689bf307d376574228516e7c8d02";
    public static final String TOKEN = "928a91b850b0f01357f616465b1725fc";
    public static final String APPID = "bb4bcc65ea8b49999ecda3ba1bcb2f2e";
    public static final String TEMPLATEID = "313856";

    public static final String SMS_VALIDATE_CODE = "SMS_VALIDATE_CODE";
    public static final String SMS_VALIDATE_PHONE = "SMS_VALIDATE_PHONE";
    public static final String SMS_VALIDATE_TIME = "SMS_VALIDATE_TIME";

    public static final String PRO_TYPE_ONE = "IT互联网";
    public static final String PRO_TYPE_TWO = "电子电气";
    public static final String PRO_TYPE_THREE = "机械制造";
    public static final String PRO_TYPE_FOUR = "媒体设计";
    public static final String PRO_TYPE_FIVE = "外语外贸";
    public static final String PRO_TYPE_SIX = "教育咨询";
    public static final String PRO_TYPE_SEVEN = "其他类型";

}

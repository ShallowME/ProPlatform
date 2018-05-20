package com.skyworth.utils.sms;

import com.skyworth.utils.ConstantHolder;

/**
 * Created by Shallow on 2018/4/24.
 */
public abstract class SmsSender {

    /**
     *
     * @param sid
     * @param token
     * @param appid
     * @param templateid
     * @param param
     * @param mobile
     * @param uid
     * @return
     */
    public abstract String sendSms(String sid, String token, String appid, String templateid, String param, String mobile, String uid);

    /**
     *
     * @param sid
     * @param token
     * @param appid
     * @param templateid
     * @param param
     * @param mobile
     * @param uid
     * @return
     */
    public abstract String sendSmsBatch(String sid, String token, String appid, String templateid, String param, String mobile, String uid);

    /**
     *
     * @param sid
     * @param token
     * @param appid
     * @param type
     * @param template_name
     * @param autograph
     * @param content
     * @return
     */
    public abstract String addSmsTemplate(String sid, String token, String appid, String type, String template_name, String autograph, String content);

    /**
     *
     * @param sid
     * @param token
     * @param appid
     * @param templateid
     * @param page_num
     * @param page_size
     * @return
     */
    public abstract String getSmsTemplate(String sid, String token, String appid, String templateid, String page_num, String page_size);

    /**
     *
     * @param sid
     * @param token
     * @param appid
     * @param templateid
     * @param type
     * @param template_name
     * @param autograph
     * @param content
     * @return
     */
    public abstract String editSmsTemplate(String sid, String token, String appid, String templateid, String type, String template_name, String autograph, String content);

    /**
     *
     * @param sid
     * @param token
     * @param appid
     * @param templateid
     * @return
     */
    public abstract String deleterSmsTemplate(String sid, String token, String appid, String templateid);

    public StringBuffer buildSmsBaseUrl(){
        StringBuffer stringBuffer = new StringBuffer("https://");
        stringBuffer.append(ConstantHolder.SERVER).append("/ol/sms");
        return stringBuffer;
    }
}

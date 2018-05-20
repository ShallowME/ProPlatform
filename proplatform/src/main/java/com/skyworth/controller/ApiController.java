package com.skyworth.controller;


import com.alibaba.fastjson.JSONObject;
import com.skyworth.utils.ConstantHolder;
import com.skyworth.utils.shiro.session.CustomSessionDAO;
import com.skyworth.utils.sms.JsonSmsSender;
import com.skyworth.utils.sms.SmsSender;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by Shallow on 2018/4/15.
 * 公共api
 */

@RestController
public class ApiController {

    private Logger logger = LoggerFactory.getLogger(ApiController.class);

    private SmsSender smsSender = new JsonSmsSender();

    @Resource
    private CustomSessionDAO customSessionDAO;

    @RequestMapping(value = "identity/{phoneNum}",method = RequestMethod.GET)
    public void identity(@PathVariable(value = "phoneNum") String phoneNum){

        String smsResponseString = null;

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        String uid = (String) session.getId();
        String code = buildIdCode();

        smsResponseString = smsSender.sendSms(ConstantHolder.SID,ConstantHolder.TOKEN,ConstantHolder.APPID,ConstantHolder.TEMPLATEID,code,phoneNum,uid);

        JSONObject smsResponseObject = JSONObject.parseObject(smsResponseString);
        String responseCode = smsResponseObject.getString("code");
        if (!responseCode.equals("000000")){
            logger.error("Send SMS failed.Response code is " + responseCode);
        }else {
            session.setAttribute(ConstantHolder.SMS_VALIDATE_PHONE, phoneNum);
            session.setAttribute(ConstantHolder.SMS_VALIDATE_CODE, smsResponseObject.getString("mobile"));
            session.setAttribute(ConstantHolder.SMS_VALIDATE_TIME, smsResponseObject.getString("create_date"));
        }
        customSessionDAO.update(session);
    }

    private String buildIdCode(){
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < 6; i++){
            code.append(random.nextInt(10));
        }
        return code.toString();
    }



}



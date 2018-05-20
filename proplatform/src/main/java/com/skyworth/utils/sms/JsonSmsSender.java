package com.skyworth.utils.sms;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Shallow on 2018/4/24.
 */
public class JsonSmsSender extends SmsSender {

    private static Logger logger = LoggerFactory.getLogger(JsonSmsSender.class);

    @Override
    public String sendSms(String sid, String token, String appid, String templateid, String param, String mobile, String uid) {

        String result = "";

        try {
            String url = buildSmsBaseUrl().append("/sendsms").toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token",token);
            jsonObject.put("appid",appid);
            jsonObject.put("templateid",templateid);
            jsonObject.put("param",param);
            jsonObject.put("mobile",mobile);
            jsonObject.put("uid",uid);

            String body = jsonObject.toJSONString();

            result = HttpClientUtil.postJson(url,body,null);

        }catch (Exception e){
            logger.error("Sms sending error", e);
        }
        return result;
    }

    @Override
    public String sendSmsBatch(String sid, String token, String appid, String templateid, String param, String mobile, String uid) {
        String result = "";

        try {
            String url = buildSmsBaseUrl().append("/sendsms_batch").toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token",token);
            jsonObject.put("appid",appid);
            jsonObject.put("templateid",templateid);
            jsonObject.put("param",param);
            jsonObject.put("mobile",mobile);
            jsonObject.put("uid",uid);

            String body = jsonObject.toJSONString();

            result = HttpClientUtil.postJson(url,body,null);

        }catch (Exception e){
            logger.error("Sms batch sending error", e);
        }
        return result;
    }

    @Override
    public String addSmsTemplate(String sid, String token, String appid, String type, String template_name, String autograph, String content) {
        String result = "";

        try {
            String url = buildSmsBaseUrl().append("/addsmstemplate").toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token",token);
            jsonObject.put("appid",appid);
            jsonObject.put("type",type);
            jsonObject.put("template_name",template_name);
            jsonObject.put("autograph",autograph);
            jsonObject.put("content",content);

            String body = jsonObject.toJSONString();

            result = HttpClientUtil.postJson(url,body,null);

        }catch (Exception e){
            logger.error("Template adding error", e);
        }
        return result;
    }

    @Override
    public String getSmsTemplate(String sid, String token, String appid, String templateid, String page_num, String page_size) {
        String result = "";

        try {
            String url = buildSmsBaseUrl().append("/getsmstemplate").toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token",token);
            jsonObject.put("appid",appid);
            jsonObject.put("page_num",page_num);
            jsonObject.put("page_size",page_size);
            String body = jsonObject.toJSONString();

            result = HttpClientUtil.postJson(url,body,null);

        }catch (Exception e){
            logger.error("Template getting error", e);
        }
        return result;
    }

    @Override
    public String editSmsTemplate(String sid, String token, String appid, String templateid, String type, String template_name, String autograph, String content) {
        String result = "";

        try {
            String url = buildSmsBaseUrl().append("/editsmstemplate").toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token",token);
            jsonObject.put("appid",appid);
            jsonObject.put("type",type);
            jsonObject.put("template_name",template_name);
            jsonObject.put("autograph",autograph);
            jsonObject.put("content",content);

            String body = jsonObject.toJSONString();

            result = HttpClientUtil.postJson(url,body,null);

        }catch (Exception e){
            logger.error("Template editing error", e);
        }
        return result;
    }

    @Override
    public String deleterSmsTemplate(String sid, String token, String appid, String templateid) {
        String result = "";

        try {
            String url = buildSmsBaseUrl().append("/addsmstemplate").toString();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            jsonObject.put("token",token);
            jsonObject.put("appid",appid);
            jsonObject.put("templateid",templateid);

            String body = jsonObject.toJSONString();

            result = HttpClientUtil.postJson(url,body,null);

        }catch (Exception e){
            logger.error("Template deleting error", e);
        }
        return result;
    }
}

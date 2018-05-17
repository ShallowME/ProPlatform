package com.skyworth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.skyworth.dto.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml","classpath:spring/spring-web.xml"})
public class UserControllerTest extends TestCase {
    @Autowired
    private WebApplicationContext wac;

    //Spring提供的测试类
    private MockMvc mockMvc;
    /**
     * 初始化SpringmvcController类测试环境
     */
    @Before
    public void setup(){
        //加载web容器上下文
        mockMvc=  MockMvcBuilders.webAppContextSetup(wac).build();

    }
    @Test
    public void uploadHead() throws Exception {

    }

    @Test
    public void showUserInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/userInfo/show/Leo")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print());//print

    }

    @Test
    public void updateUserInfo() throws Exception {
        UserInfoDto userInfoDto=new UserInfoDto();
        userInfoDto.setUserSex("woman");
        userInfoDto.setUserProvince("GuangDong");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(userInfoDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/userInfo/alter/Leo")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print());//print
    }

    @Test
    public void updatePhone() throws Exception {
        Map<String,Object>map=new HashMap<>();
        map.put("newPhoneNum","15521328682");
        map.put("idCode","1");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(map);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/userInfo/alterPhone/Leo")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print());//print
    }

    @Test
    public void uploadResumeHead() {
    }

    @Test
    public void showResumeInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/resume/show/Leo")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());//print
    }

    @Test
    public void updateResume() throws Exception {
        ResumeDto resumeDto=new ResumeDto();
        resumeDto.setResumeSex("man");
        resumeDto.setResumeSchool("Peking University");
        resumeDto.setResumeMailbox("15473222@163.com");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(resumeDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/resume/alter/Leo")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print());//print
    }

    @Test
    public void uploadCertificationHead() {
    }

    @Test
    public void updatePatent() throws Exception {
        PatentDto patentDto=new PatentDto();
        patentDto.setPatentName("newName");
        patentDto.setImgUrl("patentImgNewURL");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(patentDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/patent/alter/Leo/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print());//print
    }

    @Test
    public void showPatent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/patent/show/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());//print
    }

    @Test
    public void saveSubscribe() throws Exception {
        SubscribeDto subscribeDto=new SubscribeDto("subscribeSpotInstance","subscribeTypeInstance",5 ,25);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(subscribeDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/subscribe/alter/Leo")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print());//print
    }

    @Test
    public void deleteSubscribe() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/subscribe/delete/Leo/3")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print());//print
    }

    @Test
    public void showSubscribe() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/subscribe/show/Leo")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());//print
    }
}
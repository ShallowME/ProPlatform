package com.skyworth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.skyworth.dto.CompanyInfoDto;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml","classpath:spring/spring-web.xml"})
public class CompanyControllerTest extends TestCase {



    //Spring提供的测试类
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    /**
     * 初始化SpringmvcController类测试环境
     */
    @Before
    public void setup(){
        //加载web容器上下文
        mockMvc=  MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void uploadCompanyLogo() {
    }

    @Test
    public void showCompanyInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/company/companyInfo/show/1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print());//print


        System.out.println("=====客户端获得反馈数据=====" );
    }

    public CompanyControllerTest() {
    }

    @Test
    public void updateCompanyInfo() throws Exception {
        CompanyInfoDto companyInfoDto=new CompanyInfoDto();
        companyInfoDto.setCompanyRealname("wangyi");
        companyInfoDto.setCompanyDescription("rich");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(companyInfoDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/company/companyInfo/alter/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print());//print


        System.out.println("=====客户端获得反馈数据=====" );
    }
}
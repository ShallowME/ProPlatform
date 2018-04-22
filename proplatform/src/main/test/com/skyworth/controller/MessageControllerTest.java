package com.skyworth.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml","classpath:spring/spring-web.xml"})
@Rollback
@Transactional
public class MessageControllerTest {

    @Autowired
    private MessageController messageController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }


    @Test
    public void checkUserMessage() throws Exception {
        String content = mockMvc.perform(MockMvcRequestBuilders
                .get("/user/message/show/1/1/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void changeStateForUser() {

    }

    @Test
    public void checkCompanyMessage() throws Exception {
        String content = mockMvc.perform(MockMvcRequestBuilders
                .get("/company/message/show/1/1/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void changeStateForCompany() {
    }
}
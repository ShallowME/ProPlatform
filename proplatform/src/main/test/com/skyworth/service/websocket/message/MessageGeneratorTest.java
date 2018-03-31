package com.skyworth.service.websocket.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml","classpath:spring/spring-web.xml"})
//@Rollback
//@Transactional
public class MessageGeneratorTest {

    @Autowired
    private MessageGenerator generator;
    @Test
    public void generate() {
        boolean b = generator.generate("z3", 1, null, "message content...");
        System.out.println(b);
    }
}
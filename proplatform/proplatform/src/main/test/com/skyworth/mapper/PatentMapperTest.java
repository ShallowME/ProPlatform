package com.skyworth.mapper;

import com.skyworth.model.Patent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class PatentMapperTest {
    @Autowired
    private PatentMapper mapper;
    @Test
    public void save() {
        Patent patent = new Patent();
        patent.setUserId(1);
        patent.setPatentName("proplatform");
        mapper.save(patent);

    }

    @Test
    public void update() {
        Patent patent = new Patent();
        patent.setUserId(1);
        patent.setPatentApplyDate(1l);
        patent.setPatentAuthDate(3l);
        patent.setPatentCertiCode("patent1");
        patent.setPatentImg("img");
        patent.setPatentOwner("use1");
        patent.setPatentContact("contact");
        mapper.update(patent);

    }

    @Test
    public void findPatent() {
        List<Patent> patent = mapper.findPatentByUserId(1);
        System.out.println(patent);
    }

    @Test
    public void findByPatentName() {
        Patent patent = mapper.findByPatentName("proplatform");
        System.out.println(patent);
    }

    @Test
    public void countByPatentName() {
        int count = mapper.countByPatentName("proplatform");
        System.out.println(count);
    }

    @Test
    public void findByPatentOwner() {
        List<Patent> patents = mapper.findByPatentOwner("user1");
        System.out.println(patents);
    }
}
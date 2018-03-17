package com.skyworth.mapper;

import com.skyworth.model.Subscribe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class SubscribeMapperTest {
    @Autowired
    private SubscribeMapper mapper;

    @Test
    public void save() {
        Subscribe subscribe = new Subscribe();
        subscribe.setUserId(2);
        subscribe.setSubType("ee");
        mapper.save(subscribe);

    }

    @Test
    public void update() {
        Subscribe subscribe = new Subscribe();
        subscribe.setId(3);
        subscribe.setSubType("software");
        subscribe.setSubMaxPay(3000);
        subscribe.setSubMinPay(1000);
        subscribe.setSubSpot("spot");
        mapper.update(subscribe);
    }

    @Test
    public void findSubscribe() {
        List<Subscribe> subscribes = mapper.findSubscribeByUserId(1);
        System.out.println(subscribes);

    }

    @Test
    public void findByUserIdAndType() {
        List<Subscribe> subscribes = mapper.findByUserIdAndType(1, "cs");
        System.out.println(subscribes);
    }

    @Test
    public void countByUserId() {
        int count = mapper.countByUserId(1);
        System.out.println(count);
    }

    @Test
    public void countByUserIdAndType() {
        int count = mapper.countByUserIdAndType(2, "ee");
        System.out.println(count);
    }

    @Test
    public void saveByBatch() {
        Subscribe subscribe = new Subscribe();
        subscribe.setUserId(1);
        subscribe.setSubType("ee");

        Subscribe subscribe1 = new Subscribe();
        subscribe1.setUserId(2);
        subscribe1.setSubType("cs");

        List<Subscribe> list = new ArrayList<>(Arrays.asList(subscribe, subscribe1));
        mapper.saveByBatch(list);

    }

    @Test
    public void delete() {
        mapper.delete(4);
    }

    @Test
    public void deleteByBatch() {
        mapper.deleteByBatch(Arrays.asList(2, 4));
    }
}
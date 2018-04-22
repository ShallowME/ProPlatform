package com.skyworth.mapper;

import com.skyworth.model.Stage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class StageMapperTest {

    @Autowired
    private StageMapper mapper;

    @Test
    public void save() {
        Stage stage = new Stage();
        stage.setProId(1);
        stage.setStageNum(1);
        mapper.save(stage);

    }

    @Test
    public void update() {
        Stage stage = new Stage();
        stage.setId(1);
        stage.setStageNum(2);
        stage.setStageStartTime(new Date().getTime());
        stage.setStageEndTime(new Date().getTime());
        stage.setStageSettleTime(new Date().getTime());
        stage.setStageSpeed(4);
        mapper.update(stage);

    }

    @Test
    public void delete() {
    }

    @Test
    public void findStageByProId() {
        List<Stage> stages = mapper.findStageByProId(1);
        stages.forEach(System.out::println);
    }

    @Test
    public void findStageById() {
        Stage stage = mapper.findStageById(1);
        System.out.println(stage);

    }
}
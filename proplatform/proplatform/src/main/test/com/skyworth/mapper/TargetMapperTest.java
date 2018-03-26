package com.skyworth.mapper;

import com.skyworth.model.Target;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class TargetMapperTest {
    @Autowired
    private TargetMapper mapper;
    @Test
    public void save() {
        Target target = new Target();
        target.setStageId(2);
        target.setTargetDeadline(30l);
        mapper.save(target);

    }

    @Test
    public void update() {
        Target target = new Target();
        target.setId(1);
//        target.setTargetDeadline(20);
        target.setTargetDetail("detail2");
        target.setTargetRemarks("remarks2");
        System.out.println(target);
        mapper.update(target);
    }

    @Test
    public void delete() {
        mapper.delete(2);

    }

    @Test
    public void findById() {
        Target target = mapper.findById(1);
        System.out.println(target);

    }

    @Test
    public void findByStageId() {
        List<Target> targets = mapper.findByStageId(1);
        System.out.println(targets);
    }
}
package com.skyworth.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class WorkWorkFileMapperTest {
    @Autowired
    private WorkFileMapper mapper;
    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void updateByUser() {
    }

    @Test
    public void updateByStage() {
    }

    @Test
    public void updateByCompany() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findFileByStageId() {
    }

    @Test
    public void findFileByUserId() {
    }

    @Test
    public void findFileByCompanyId() {
    }

    @Test
    public void deleteByUser() {
    }

    @Test
    public void deleteByCompany() {
    }
}
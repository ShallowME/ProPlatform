package com.skyworth.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class InviteMapperTest {
    @Autowired
    private InviteMapper mapper;
    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteByBatch() {
    }

    @Test
    public void deleteByCompany() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findInvitesByCompanyId() {
    }
}
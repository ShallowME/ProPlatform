package com.skyworth.mapper;

import com.skyworth.model.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class PermissionMapperTest {

    @Autowired
    private PermissionMapper mapper;
    @Test
    public void save() {
        Permission permission = new Permission("权限2", "仅查看个人信息");
        mapper.save(permission);
    }

    @Test
    public void update() {
        Permission permission = new Permission("权限1", "查看和修改个人信息");
        mapper.update(permission);

    }

    @Test
    public void findPermission() {
        Permission permission = mapper.findPermission("权限1");
        System.out.println(permission);

    }
}
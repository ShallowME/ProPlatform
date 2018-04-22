package com.skyworth.mapper;

import com.skyworth.model.RolePermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class RolePermissionMapperTest {
    @Autowired
    private RolePermissionMapper mapper;
    @Test
    public void save() {
        RolePermission rolePermission = new RolePermission(1, 2);
        mapper.save(rolePermission);

    }

    @Test
    public void update() {
        mapper.update(1,3,1);
    }

    @Test
    public void findRolePermissionByRoleId() {
        List<RolePermission> list = mapper.findRolePermissionByRoleId(1);
        System.out.println(list);

    }

    @Test
    public void findRolePermissionByPermissionId() {
        List<RolePermission> list = mapper.findRolePermissionByPermissionId(2);
        System.out.println(list);

    }
}
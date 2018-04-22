package com.skyworth.mapper;

import com.skyworth.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class HostRoleMapperTest {
    @Autowired
    private HostRoleMapper mapper;
    @Test
    public void saveUserRole() {
        UserRole userRole = new UserRole(1, 1);
        mapper.saveUserRole(userRole);

    }

    @Test
    public void saveCompanyRole() {
    }

    @Test
    public void updateByUserId() {
        UserRole userRole = new UserRole(1, 1);
        mapper.updateByUserId(userRole, 2);
    }

    @Test
    public void updateByCompanyId() {
    }

    @Test
    public void findHostRoleByUserId() {
        List<UserRole> list = mapper.findUserRoleById(1);
        System.out.println(list);

    }

    @Test
    public void findHostRoleByCompanyId() {
    }
}
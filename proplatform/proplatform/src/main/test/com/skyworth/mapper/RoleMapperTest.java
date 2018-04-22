package com.skyworth.mapper;

import com.skyworth.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class RoleMapperTest {
    @Autowired
    private RoleMapper mapper;
    @Test
    public void save() {
        Role role = new Role("管理员", "管理后台服务");
        mapper.save(role);
    }

    @Test
    public void update() {
        Role role = new Role("管理员", "管理后台数据服务");
        mapper.update(role);

    }

    @Test
    public void findRole() {
        Role role = mapper.findRole("管理员");
        System.out.println(role);

    }
}
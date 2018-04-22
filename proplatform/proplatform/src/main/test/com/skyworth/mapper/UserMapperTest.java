package com.skyworth.mapper;

import com.skyworth.model.User;
import com.skyworth.service.userService.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/mybatis-config.xml"})
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private DataSource dataSource;

    @Test
    public void dataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void save() {
        User user = new User("z3", "123456", "z3pwd", "z3", new Date());
        mapper.save(user);
    }

    @Test
    public void countByName() {
        int count = mapper.countByName("z1");
        System.out.println(count);

    }

    @Test
    public void countByPhoneNum() {
        int count = mapper.countByPhoneNum("123456");
        System.out.println(count);

    }

    @Test
    public void countUser() {
        long count = mapper.countUser();
        System.out.println(count);

    }

    @Test
    public void findByUsernameAndPassword() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findByPhoneNum() {
    }

    @Test
    public void updatePassword() {
    }
}
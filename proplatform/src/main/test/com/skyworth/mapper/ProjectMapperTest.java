package com.skyworth.mapper;

import com.skyworth.model.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class ProjectMapperTest {
    @Autowired
    private ProjectMapper mapper;

    @Test
    public void save() {
        Project project = new Project();
        project.setCompanyId(1);
        project.setCompanyName("创维");
        project.setProName("proplatform");
        mapper.save(project);

    }

    @Test
    public void saveByBatch() {
        Project project = new Project();
        project.setCompanyId(2);
        project.setCompanyName("huawei");
        project.setProName("proplatform2");

        Project project1 = new Project();
        project1.setCompanyId(3);
        project1.setCompanyName("腾讯");
        project1.setProName("proplatform3");

        Project project2 = new Project();
        project2.setCompanyId(4);
        project2.setCompanyName("baidu");
        project2.setProName("proplatform4");

        mapper.saveByBatch(Arrays.asList(project, project1, project2));

    }

    @Test
    public void update() {
        Project project = new Project();
        project.setId(1);
        project.setProType("cs");
        project.setProCycle(40);
        project.setProMoney(2000.0);
        project.setProPubTime(new Date().getTime());
        project.setProState(2);
        mapper.update(project);
    }

    @Test
    public void delete() {
        mapper.delete(4);
    }

    @Test
    public void deleteByBatch() {
        mapper.deleteByBatch(Arrays.asList(2, 3));
    }

    @Test
    public void countByCompanyName() {
        long count = mapper.countByCompanyName("创维");
        System.out.println(count);

    }

    @Test
    public void countByName() {
        long count = mapper.countByName("proplatform");
        System.out.println(count);
    }

    @Test
    public void countByType() {
        long count = mapper.countByType("cs");
        System.out.println(count);

    }

    @Test
    public void findByCompanyId() {
        List<Project> projects = mapper.findByCompanyId(1);
        System.out.println(projects);

    }

    @Test
    public void findByProName() {
        List<Project> projects = mapper.findByProName("ppp");
        projects.forEach(System.out::println);

    }

    @Test
    public void findByCompanyName() {
        List<Project> projects = mapper.findByCompanyName("huawei");
        projects.forEach(System.out::println);
    }

    @Test
    public void findByMoney() {
        List<Project> projects = mapper.findByMoney(1000, 2000);
        projects.forEach(System.out::println);

    }

    @Test
    public void findByType() {
        List<Project> projects = mapper.findByType("cs");
        projects.forEach(System.out::println);

    }

    @Test
    public void findByCycle() {
        List<Project> projects = mapper.findByCycle(20, 50);
        projects.forEach(System.out::println);

    }

    @Test
    public void findByPubTime() {
        List<Project> projects = mapper.findByPubTime(0, new Date().getTime());
        projects.forEach(System.out::println);
    }
}
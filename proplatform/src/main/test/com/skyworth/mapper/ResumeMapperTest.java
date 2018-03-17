package com.skyworth.mapper;

import com.skyworth.model.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class ResumeMapperTest {

    @Autowired
    private ResumeMapper mapper;
    @Test
    public void save() {
        Resume resume = new Resume();
        resume.setUserId(1);
        resume.setResumeSchool("SCUT");
        mapper.save(resume);
    }

    @Test
    public void update() {
        Resume resume = new Resume();
        resume.setUserId(1);
        resume.setResumeSex("male");
        resume.setResumeAddress("guangzhou");
        resume.setResumeImg("img");
        resume.setResumeMailbox("1@qq.com");
        resume.setResumeMajor("software engineering");
        resume.setResumeRealname("li");
        resume.setResumeSchExperience("none");
        resume.setResumeWorkExperience("none");
        resume.setResumePhonenum("111");
        resume.setResumeAddress("shenzhen");
        mapper.update(resume);
    }

    @Test
    public void findByUserId() {
        Resume resume = mapper.findByUserId(1);
        System.out.println(resume);
    }

    @Test
    public void countByUserId() {
        int count = mapper.countByUserId(2);
        System.out.println(count);
    }
}
package com.skyworth.service.userService;

import com.skyworth.dto.ApplyDto;
import com.skyworth.dto.UserDto;
import com.skyworth.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml","classpath:spring/mybatis-config.xml"})
public class UserServiceTest {
    @Autowired
    private UserService service;
    @Test
    public void register() {
        UserDto user = new UserDto();
        user.setUserName("z6");
        user.setPassword("z6pwd");
        user.setPhoneNum("111");
        service.register(user);
    }

    @Test
    public void checkUserExist() {
        boolean b = service.checkUserExist("z3");
        System.out.println(b);
    }

    @Test
    public void getByUsernameAndPassword() {
        User user = new User();
        user.setUserName("z6");
        user.setUserPassword("z6pwd");
        User user1 = service.getByUsernameAndPassword(user);
        System.out.println(user1);

    }

    @Test
    public void getByUsername() {
        User user = service.getByUsername("z6");
        System.out.println(user);

    }

    @Test
    public void getByPhoneNum() {
        User user = service.getByPhoneNum("111");
        System.out.println(user);
    }

    @Test
    public void updatePassword() {
        service.updatePasswordByPhoneNum("123456", "wwypwd");
    }

    @Test
    public void setInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(3);
        userInfo.setUserRealname("wwy");
        userInfo.setUserSchool("SCUT");
        userInfo.setUserSex("male");
        userInfo.setUserProvince("广东");
        userInfo.setUserCity("广州");
        userInfo.setUserMajor("自动化");
        userInfo.setUserMailbox("202020@163.com");
        service.setInfo(userInfo);
    }

    @Test
    public void updateInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        userInfo.setUserMajor("ee");
        userInfo.setUserUrl("url");
        service.updateInfo(userInfo);
    }

    @Test
    public void getInfo() {
        UserInfo info = service.getInfo(1);
        System.out.println(info);

    }

    @Test
    public void removeInfo() {
        boolean b = service.removeInfo(3);
        System.out.println(b);
    }

    @Test
    public void setResume() {
        Resume resume = new Resume();
        resume.setUserId(3);
        resume.setResumePhoneNum("222");
        resume.setResumeRealname("user2");
        service.setResume(resume);
    }

    @Test
    public void updateResume() {
        Resume resume = new Resume();
        resume.setUserId(2);
        resume.setResumeWorkExperience("none");
        service.updateResume(resume);
    }

    @Test
    public void getResume() {
        Resume resume = service.getResume(1);
        System.out.println(resume);
    }

    @Test
    public void removeResume() {
        boolean b = service.removeResume(3);
        System.out.println(b);
    }

    @Test
    public void setPatent() {
        Patent patent = new Patent();
        patent.setUserId(3);
        patent.setPatentOwner("user2");
        patent.setPatentName("p2");
        service.setPatent(patent);
    }

    @Test
    public void updatePatent() {
        Patent patent = new Patent();
        patent.setId(2);
        patent.setPatentContact("contact");
        patent.setPatentImg("img2");
        service.updatePatent(patent);
    }

    @Test
    public void removePatent() {
        boolean b = service.removePatent(3);
        System.out.println(b);

    }

    @Test
    public void getPatentById() {
        Patent patent = service.getPatentById(1);
        System.out.println(patent);
    }

    @Test
    public void getPatentByUserId() {
        List<Patent> patent = service.getPatentByUserId(2);
        System.out.println(patent);
    }

    @Test
    public void setSubscribe() {
        Subscribe subscribe = new Subscribe();
        subscribe.setSubType("cs");
        subscribe.setUserId(2);
        subscribe.setSubSpot("spot2");
        service.setSubscribe(subscribe);
    }

    @Test
    public void updateSubscribe() {
        Subscribe subscribe = new Subscribe();
        subscribe.setId(6);
        subscribe.setSubMinPay(2000);
        subscribe.setSubMaxPay(3000);
        service.updateSubscribe(subscribe);

    }

    @Test
    public void getSubscribeById() {
        Subscribe subscribe = service.getSubscribeById(6);
        System.out.println(subscribe);
    }

    @Test
    public void getSubscribe() {
        List<Subscribe> subscribes = service.getSubscribeByUserId(2);
        subscribes.forEach(System.out::println);
    }

    @Test
    public void removeSubscribe() {
        boolean b = service.removeSubscribe(5);
        System.out.println(b);

    }

    @Test
    public void removeSubscribeByBatch() {
        boolean b = service.removeSubscribeByBatch(Arrays.asList(1, 7));
        System.out.println(b);
    }

    @Test
    public void saveFile() {
        WorkFile workFile = new WorkFile();
        workFile.setUserId(2);
        workFile.setWorkFileName("file2");
        service.saveFile(workFile);
    }

    @Test
    public void updateFile() {
        WorkFile workFile = new WorkFile();
        workFile.setId(5);
        workFile.setWorkFileModifyDate(new Date().getTime());
        workFile.setWorkFileSize(10.0);
        workFile.setWorkFileUploader("user1");
        workFile.setWorkFileUrl("url1");
        workFile.setStageId(1);
        workFile.setUserId(1);
        service.updateFile(workFile);
    }

    @Test
    public void getFileById() {
        WorkFile workFile = service.getFileById(5);
        System.out.println(workFile);
    }

    @Test
    public void getFilesByUserId() {
        List<WorkFile> workFiles = service.getFilesByUserId(1);
        workFiles.forEach(System.out::println);
    }

    @Test
    public void removeFile() {

    }

    @Test
    public void removeFileByBatch() {

    }

    @Test
    public void saveApply() {
        ApplyDto apply = new ApplyDto();
        apply.setApplicantId(1);
        apply.setProId(5);
        apply.setResumeId(1);
        service.saveApply(apply);
    }

    @Test
    public void updateApply() {
        Apply apply = new Apply();
        apply.setId(3);
        apply.setApplyMark(100);
        apply.setApplyState(10);
        apply.setCompanyId(1);
        service.updateApply(apply);
    }

    @Test
    public void getApplyByUserId() {

        List<Apply> applies = service.getApplyByUserId(1);
        applies.forEach(System.out::println);
    }

    @Test
    public void getApplyById() {
        Apply apply = service.getApplyById(3);
        System.out.println(apply);
    }

    @Test
    public void saveMessage() {
        Message message = new Message();
        message.setUserId(1);
        message.setMesContent("content2");
        message.setMesState(200);
        service.saveMessage(message);
    }

    @Test
    public void getMessageByUserId() {
        List<Message> messages = service.getMessageByUserId(1);
        messages.forEach(System.out::println);
    }

    @Test
    public void getMessageById() {
        Message message = service.getMessageById(1);
        System.out.println(message);

    }

    @Test
    public void removeMessage() {
        boolean b = service.removeMessage(6);
        System.out.println(b);
    }

    @Test
    public void removeMessageByBatch() {
        boolean b = service.removeMessageByBatch(Arrays.asList(4, 5));
        System.out.println(b);
    }
}
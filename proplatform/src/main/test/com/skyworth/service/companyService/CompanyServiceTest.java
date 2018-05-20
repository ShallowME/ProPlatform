package com.skyworth.service.companyService;

import com.skyworth.dto.CompanyDto;
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
public class CompanyServiceTest {
    @Autowired
    private CompanyService service;
    @Test
    public void register() {
        CompanyDto company = new CompanyDto();
        company.setCompanyName("网易");
        company.setPassword("wangyipwd");
        boolean b = service.register(company);

    }

    @Test
    public void checkCompanyExist() {
        boolean b = service.checkCompanyExist("baidu");
        System.out.println(b);
    }

    @Test
    public void getByCompanyNameAndPassword() {
        Company company = service.getByCompanyNameAndPassword("腾讯", "tencentpwd");
        System.out.println(company);

    }

    @Test
    public void getByPhoneNumAndPassword() {
        Company company = service.getByPhoneNumAndPassword("222", "huaweipwd");
        System.out.println(company);

    }

    @Test
    public void getByCompanyName() {
        Company company = service.getByCompanyName("创维");
        System.out.println(company);
    }

    @Test
    public void getByPhoneNum() {
        Company company = service.getByPhoneNum("");
        System.out.println(company);

    }

    @Test
    public void updatePassword() {
        service.updatePasswordByPhoneNum("123456789", "wypwd");

    }

    @Test
    public void setInfo() {
        CompanyInfo info = new CompanyInfo();
        info.setCompanyId(2);
        info.setCompanyLogo("logo2");
        boolean b = service.setInfo(info);
        System.out.println(b);
    }

    @Test
    public void updateInfo() {
        CompanyInfo info = new CompanyInfo();
        info.setCompanyId(1);
        info.setCompanyRealname("创维");
        info.setCompanyMajor("TV");
        info.setCompanyDescription("...........description.............");
        boolean b = service.updateInfo(info);
        System.out.println(b);
    }

    @Test
    public void getInfo() {
        CompanyInfo info = service.getInfo(2);
        System.out.println(info);
    }

    @Test
    public void removeInfo() {
        boolean b = service.removeInfo(2);
        System.out.println(b);
    }

    @Test
    public void saveProject() {
        Project project = new Project();
        project.setProName("project1");
        project.setCompanyId(1);
        project.setCompanyName("创维");
        project.setProPubTime(new Date().getTime());
        project.setProMoney(2000.0);
        project.setProType("software");
        service.saveProject(project);
    }

    @Test
    public void updateProject() {
        Project project = new Project();
        project.setId(8);
        project.setProCycle(30);
        project.setProPubTime(new Date().getTime());
        project.setProMoney(2000.0);
        project.setProDescription(".......description...........");
        project.setProRequest("......request......");
        project.setProState(2);
        service.updateProject(project);
    }

    @Test
    public void removeProject() {
        boolean b = service.removeProject(8);
        System.out.println(b);
    }

    @Test
    public void checkProjects() {
        List<Project> projects = service.checkProjects(2);
        projects.forEach(System.out::println);
    }

    @Test
    public void saveStage() {
        Stage stage = new Stage();
        stage.setProId(1);
        stage.setStageStartTime(new Date().getTime());
        stage.setStageEndTime(new Date().getTime());
        stage.setStageNum(1);
        service.saveStage(stage);
    }

    @Test
    public void updateStage() {
        Stage stage = new Stage();
        stage.setProId(1);
        stage.setStageSettleTime(new Date().getTime());
        stage.setStageSpeed(3);
        service.updateStage(stage);
    }

    @Test
    public void removeStage() {
        boolean b = service.removeStage(3);
        System.out.println(b);
    }

    @Test
    public void getAllStage() {
        List<Stage> allStage = service.getAllStage(1);
        allStage.forEach(System.out::println);
    }

    @Test
    public void getStage() {
        Stage stage = service.getStage(1);
        System.out.println(stage);
    }

    @Test
    public void saveTarget() {
        Target target = new Target();
        target.setStageId(2);
        target.setTargetDeadline(new Date().getTime());
        service.saveTarget(target);
    }

    @Test
    public void updateTarget() {
        Target target = new Target();
        target.setStageId(2);
        target.setTargetDeadline(new Date().getTime());
        service.saveTarget(target);
    }

    @Test
    public void removeTarget() {
        boolean b = service.removeTarget(5);
        System.out.println(b);
    }

    @Test
    public void removeTargetByBatch() {
        boolean b = service.removeTargetByBatch(Arrays.asList(2, 4));
        System.out.println(b);
    }

    @Test
    public void getTargetByStageId() {
        List<Target> targets = service.getTargetByStageId(1);
        targets.forEach(System.out::println);
    }

    @Test
    public void getTargetById() {
        Target target = service.getTargetById(1);
        System.out.println(target);

    }

    @Test
    public void saveFile() {
        WorkFile workFile = new WorkFile();
        workFile.setCompanyId(1);
        workFile.setFileName("file3");
        service.saveFile(workFile);
    }

    @Test
    public void updateFile() {
        WorkFile workFile = new WorkFile();
        workFile.setId(2);
        workFile.setFileModifyDate(new Date().getTime());
        workFile.setFileSize(10.0);
        workFile.setFileUploader("user2");
        workFile.setFileUrl("url");
        workFile.setStageId(2);
        workFile.setUserId(2);
        service.updateFile(workFile);
    }

    @Test
    public void removeFile() {
        boolean b = service.removeFile(3);
        System.out.println(b);
    }

    @Test
    public void removeFileByBatch() {
        boolean b = service.removeFileByBatch(Arrays.asList(1, 2));
        System.out.println(b);
    }

    @Test
    public void getFileById() {
        WorkFile workFile = service.getFileById(1);
        System.out.println(workFile);

    }

    @Test
    public void getFilesByCompanyId() {
        List<WorkFile> workFiles = service.getFilesByCompanyId(1);
        workFiles.forEach(System.out::println);

    }

    @Test
    public void getFilesByStageId() {
        List<WorkFile> workFiles = service.getFilesByStageId(1);
        workFiles.forEach(System.out::println);

    }

    @Test
    public void saveInvite() {
        Invite invite = new Invite();
        invite.setCompanyId(2);
        invite.setProId(6);
        invite.setInviteState(200);
        service.saveInvite(invite);
    }

    @Test
    public void updateInvite() {
        Invite invite = new Invite();
        invite.setId(1);
        invite.setResumeId(1);
        invite.setInviteMark(11);
        service.updateInvite(invite);
    }

    @Test
    public void removeInvite() {
        boolean b = service.removeInvite(5);
        System.out.println(b);
    }

    @Test
    public void removeInviteByBatch() {
        boolean b = service.removeInviteByBatch(Arrays.asList(1, 4));
        System.out.println(b);
    }

    @Test
    public void getInviteByCompany() {
        List<Invite> invites = service.getInviteByCompany(1);
        invites.forEach(System.out::println);
    }

    @Test
    public void getInviteByUser() {
        List<Invite> invites = service.getInviteByUser(1);
        invites.forEach(System.out::println);

    }

    @Test
    public void getInviteByProId() {
        List<Invite> invites = service.getInviteByProId(1);
        invites.forEach(System.out::println);
    }

    @Test
    public void saveMessage() {
        Message message = new Message();
        message.setCompanyId(null);
        message.setUserId(1);
        message.setMesContent("content");
        message.setMesState(100);
        service.saveMessage(message);
    }

    @Test
    public void removeMessage() {
        boolean b = service.removeMessage(3);
        System.out.println(b);
    }

    @Test
    public void removeMessageByBatch() {
        boolean b = service.removeMessageByBatch(Arrays.asList(1, 2));
        System.out.println(b);
    }

    @Test
    public void getMessage() {
        Message message = service.getMessage(1);
        System.out.println(message);

    }

    @Test
    public void getMessageByCompany() {
        List<Message> messages = service.getMessageByCompany(1);
        messages.forEach(System.out::println);
    }

    @Test
    public void getMessageByUser() {
        List<Message> messages = service.getMessageByUser(1);
        messages.forEach(System.out::println);

    }

}
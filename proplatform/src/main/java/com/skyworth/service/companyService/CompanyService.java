package com.skyworth.service.companyService;

import com.skyworth.model.*;

import java.util.List;
import java.util.Set;

public interface CompanyService {
    boolean register(Company company);

    boolean checkCompanyExist(String companyName);

    Company getByCompanyNameAndPassword(String companyName, String password);

    Company getByPhoneNumAndPassword(String phoneNum, String password);

    Company getByCompanyName(String companyName);

    Company getByPhoneNum(String phoneNum);

    boolean updatePassword(String companyName, String newPassword);

    boolean setInfo(CompanyInfo info);

    boolean updateInfo(CompanyInfo info);

    CompanyInfo getInfo(int companyId);

    boolean removeInfo(int companyId);

    boolean saveProject(Project project);

    boolean updateProject(Project project);

    boolean removeProject(int id);

    List<Project> checkProjects(int companyId);

    boolean saveStage(Stage stage);

    boolean updateStage(Stage stage);

    boolean removeStage(int id);

    List<Stage> getAllStage(int proId);

    Stage getStage(int id);

    boolean saveTarget(Target target);

    boolean updateTarget(Target target);

    boolean removeTarget(int targetId);

    boolean removeTargetByBatch(List<Integer> list);

    List<Target> getTargetByStageId(int stageId);

    Target getTargetById(int targetId);

    boolean saveFile(File file);

    boolean updateFile(File file);

    boolean removeFile(int fileId);

    boolean removeFileByBatch(List<Integer> list);

    File getFileById(int fileId);

    List<File> getFilesByCompanyId(int companyId);

    List<File> getFilesByStageId(int stageId);

    boolean saveInvite(Invite invite);

    boolean updateInvite(Invite invite);

    boolean removeInvite(int id);

    boolean removeInviteByBatch(List<Integer> list);

    List<Invite> getInviteByCompany(int companyId);

    List<Invite> getInviteByUser(int resumeId);

    List<Invite> getInviteByProId(int proId);

    boolean saveMessage(Message message);

    boolean removeMessage(int mesId);

    boolean removeMessageByBatch(List<Integer> list);

    List<Message> getMessageByCompany(int companyId);
    
    List<Message> getMessageByUser(int userId);

    Message getMessage(int id);

    Set<Role> getAllRoles(int companyId);

    Set<Permission> getAllPermissions(int roleId);
}

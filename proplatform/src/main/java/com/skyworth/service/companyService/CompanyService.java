package com.skyworth.service.companyService;

import com.skyworth.dto.*;
import com.skyworth.model.*;

import java.util.List;
import java.util.Set;

public interface CompanyService {
    boolean register(CompanyDto companyDto);

    Company findByCompanyId(int companyId);

    boolean checkCompanyExist(String companyName);

    Company getByCompanyNameAndPassword(String companyName, String password);

    Company getByPhoneNumAndPassword(String phoneNum, String password);

    Company getByCompanyName(String companyName);

    Company getByPhoneNum(String phoneNum);

    void updatePasswordByPhoneNum(String companyPhoneNum, String newPassword);

    void updatePasswordById(int companyId, String newPassword);

    boolean setInfo(CompanyInfo info);

    boolean updateInfo(CompanyInfo info);

    CompanyInfo getInfo(int companyId);

    boolean removeInfo(int companyId);

    void saveProject(ProjectDto projectDto);

    void saveProject(Project project);

    Project getProjectByProNameAndCompanyId(int proCompanyId, String projectName);

    void updateProject(ProjectDto projectDto);

    boolean removeProject(int id);

    List<ProjectDto> checkProjects(int companyId);

    void saveStage(StageDto stageDto);


    void updateStage(StageDto stageDto);


    boolean removeStage(int id);

    List<Stage> getAllStage(int proId);

    Stage getStage(int id);

    void saveTarget(TargetDto targetDto);

    void saveTarget(Target target);

    void updateTarget(TargetDto targetDto);

    void updateTarget(Target target);

    boolean removeTarget(int targetId);

    boolean removeTargetByBatch(List<Integer> list);

    List<Target> getTargetByStageId(int stageId);

    Target getTargetById(int targetId);

    Integer allTargetsOneStage(int stageId);

    Integer completedTargetsOneStage(int stageId);

    boolean saveFile(WorkFile workFile);

    boolean updateFile(WorkFile workFile);

    boolean removeFile(int fileId);

    boolean removeFileByBatch(List<Integer> list);

    WorkFile getFileById(int fileId);

    boolean saveInvite(InviteDto inviteDto);

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

    List<ResumeDto> getApplicationsByProjectId(int projectId);

    List<ResumeDto> getResumesByConditions(String resumeProfession, String resumeProfessionType, String resumeProvince);

    List<StageDto> getStagesByProId(int projectId);

    boolean targetCompleted(int targetId);
}

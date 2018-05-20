package com.skyworth.service.userService;

import com.skyworth.dto.ApplyDto;
import com.skyworth.dto.ProjectDto;
import com.skyworth.dto.UserDto;
import com.skyworth.model.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Shallow on 2018/3/6.
 */
public interface UserService {

    boolean register(UserDto userDto);

    boolean checkUserExist(String username);

    User getByUserId(int userId);

    User getByUsernameAndPassword(User user);

    User getByUsername(String username);

    User getByPhoneNum(String phoneNum);

    void updatePasswordByPhoneNum(String userPhoneNum, String newPassword);

    void updatePasswordById(int userId, String newPassword);

    boolean setInfo(UserInfo info);

    boolean updateInfo(UserInfo info);

    UserInfo getInfo(int userId);

    boolean removeInfo(int infoId);

    boolean setResume(Resume resume);

    boolean updateResume(Resume resume);

    Resume getResume(int userId);

    boolean removeResume(int resumeId);

    boolean setPatent(Patent patent);

    boolean updatePatent(Patent patent);

    boolean removePatent(int patentId);

    Patent getPatentById(int id);

    List<Patent> getPatentByUserId(int userId);

    boolean setSubscribe(Subscribe subscribe);

    boolean updateSubscribe(Subscribe subscribe);

    Subscribe getSubscribeById(int subId);

    List<Subscribe> getSubscribeByUserId(int userId);

    boolean removeSubscribe(int subId);

    boolean removeSubscribeByBatch(List<Integer> list);

    boolean saveFile(WorkFile workFile);

    boolean updateFile(WorkFile workFile);

    WorkFile getFileById(int fileId);
        
    List<WorkFile> getFilesByUserId(int userId);

    boolean removeFile(int fileId);

    boolean removeFileByBatch(List<Integer> list);

    boolean saveApply(ApplyDto applyDto);

    boolean updateApply(Apply apply);

    List<Apply> getApplyByUserId(int userId);

    Apply getApplyById(int id);

    boolean saveMessage(Message message);

    List<Message> getMessageByUserId(int userId);

    Message getMessageById(int id);
    
    boolean removeMessage(int mesId);

    boolean removeMessageByBatch(List<Integer> list);

    Set<Role> getAllRoles(int userId);

    Set<Permission> getAllPermissions(int roleId);

    List<ProjectDto> getProjectsByName(String proName);

    List<ProjectDto> getProjectsByType(String type);

    List<ProjectDto> getProjectsByMoney(double minMoney, double maxMoney);

    List<ProjectDto> getProjectsByCycle(int minCycle, int maxCycle);

    List<ProjectDto> orderForProjects(int orderCode);

    List<ProjectDto> getBySubscribe(int userId);

    List<ProjectDto> getProjectsByConditions(String proName, int typeCode, double minProMoney, double maxProMoney, int maxProCycle);

    ProjectDto getProjectById(int proId);

    Boolean applyForProject(int userId, int proId);

    List<ProjectDto> selectProjects(int userId, int selectCode);
}

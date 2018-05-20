package com.skyworth.service.userService;

import com.skyworth.dto.ApplyDto;
import com.skyworth.dto.ProjectDto;
import com.skyworth.dto.UserDto;
import com.skyworth.mapper.*;
import com.skyworth.model.*;
import com.skyworth.utils.ConstantHolder;
import com.skyworth.utils.PasswordEncrypt;
import com.skyworth.utils.TimeFormatUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Shallow on 2018/3/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private PatentMapper patentMapper;

    @Autowired
    private SubscribeMapper subscribeMapper;

    @Autowired
    private WorkFileMapper workFileMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ProjectMapper projectMapper;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public boolean register(UserDto userDto) {
        if (checkUserExist(userDto.getUserName())) {
            return false;
        }
        try {
            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setUserPassword(userDto.getPassword());
            user.setUserPhoneNum(userDto.getPhoneNum());
            User userLogin = PasswordEncrypt.encrypt(user);
            userMapper.save(userLogin);
        } catch (DuplicateKeyException e) {
            logger.error("Register error.", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserExist(String username) {
        return userMapper.countByName(username) >= 1;
    }

    @Override
    public User getByUserId(int userId) {
        return userMapper.fineByUserId(userId);
    }

    @Override
    public User getByUsernameAndPassword(User user) {
        return userMapper.findByUsernameAndPassword(user);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User getByPhoneNum(String phoneNum) {
        return userMapper.findByPhoneNum(phoneNum);
    }

    @Override
    public void updatePasswordByPhoneNum(String userPhoneNum, String newPassword) {
        try {
            userMapper.updateUserPasswordByPhoneNum(userPhoneNum, newPassword);
        } catch (Exception e) {
            logger.error("Updating password error", e);
        }
    }

    @Override
    public void updatePasswordById(int userId, String newPassword) {
        try {
            userMapper.updateUserPasswordById(userId, newPassword);
        } catch (Exception e) {
            logger.error("Updating password error", e);
        }
    }

    @Override
    public boolean setInfo(UserInfo info) {
        try {
            userInfoMapper.save(info);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInfo(UserInfo info) {
        try {
            userInfoMapper.update(info);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public UserInfo getInfo(int userId) {
        return userInfoMapper.findUserInfo(userId);
    }

    @Override
    public boolean removeInfo(int infoId) {
        try {
            userInfoMapper.delete(infoId);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean setResume(Resume resume) {
        try {
            resumeMapper.save(resume);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateResume(Resume resume) {
        try {
            resumeMapper.update(resume);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Resume getResume(int userId) {
        return resumeMapper.findByUserId(userId);
    }

    @Override
    public boolean removeResume(int resumeId) {
        try {
            resumeMapper.delete(resumeId);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean setPatent(Patent patent) {
        try {
            patentMapper.save(patent);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePatent(Patent patent) {
        try {
            patentMapper.update(patent);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removePatent(int patentId) {
        try {
            patentMapper.delete(patentId);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Patent getPatentById(int id) {
        return patentMapper.findPatentById(id);
    }

    @Override
    public List<Patent> getPatentByUserId(int userId) {
        return patentMapper.findPatentByUserId(userId);
    }

    @Override
    public boolean setSubscribe(Subscribe subscribe) {
        try {
            subscribeMapper.save(subscribe);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSubscribe(Subscribe subscribe) {
        try {
            subscribeMapper.update(subscribe);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Subscribe getSubscribeById(int subId) {
        return subscribeMapper.findById(subId);
    }

    @Override
    public List<Subscribe> getSubscribeByUserId(int userId) {
        return subscribeMapper.findSubscribeByUserId(userId);
    }

    @Override
    public boolean removeSubscribe(int subId) {
        try {
            subscribeMapper.delete(subId);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeSubscribeByBatch(List<Integer> list) {
        try {
            subscribeMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveFile(WorkFile workFile) {
        try {
            workFileMapper.save(workFile);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFile(WorkFile workFile) {
        try {
            workFileMapper.update(workFile);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public WorkFile getFileById(int fileId) {
        return workFileMapper.findById(fileId);
    }

    @Override
    public List<WorkFile> getFilesByUserId(int userId) {
        return workFileMapper.findFileByUserId(userId);
    }

    @Override
    public boolean removeFile(int fileId) {
        try {
            workFileMapper.delete(fileId);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeFileByBatch(List<Integer> list) {
        try {
            workFileMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveApply(ApplyDto applyDto) {

        Apply apply = new Apply();
        apply.setApplicantId(applyDto.getApplicantId());
        apply.setResumeId(applyDto.getResumeId());
        apply.setCompanyId(apply.getCompanyId());
        apply.setProId(apply.getProId());
        apply.setApplyState(apply.getApplyState());
        apply.setApplyMark(apply.getApplyMark());
        try {
            applyMapper.save(apply);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateApply(Apply apply) {
        try {
            applyMapper.update(apply);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public List<Apply> getApplyByUserId(int userId) {
        return applyMapper.findApplyByUserId(userId);
    }

    @Override
    public Apply getApplyById(int id) {
        return applyMapper.findById(id);
    }

    @Override
    public boolean saveMessage(Message message) {
        try {
            messageMapper.save(message);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public List<Message> getMessageByUserId(int userId) {
        return messageMapper.findByUserId(userId);
    }

    @Override
    public Message getMessageById(int id) {
        return messageMapper.findById(id);
    }

    @Override
    public boolean removeMessage(int mesId) {
        try {
            messageMapper.delete(mesId);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeMessageByBatch(List<Integer> list) {
        try {
            messageMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Set<Role> getAllRoles(int userId) {
        return userMapper.getAllRoles(userId);
    }

    @Override
    public Set<Permission> getAllPermissions(int roleId) {
        return roleMapper.getAllPermissions(roleId);
    }

    @Override
    public List<ProjectDto> getProjectsByName(String proName) {
        List<Project> projectsInDB = projectMapper.findByProName(proName);
        List<ProjectDto> projectsInView = new ArrayList<>();

        if (projectsInDB != null) {
            projectsInDB.forEach(project -> {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setProjectId(project.getId());
                projectDto.setCompanyId(project.getCompanyId());
                projectDto.setCompanyName(project.getCompanyName());
                projectDto.setProjectName(project.getProName());
                projectDto.setProjectMoney(project.getProMoney());
                projectDto.setProjectType(project.getProType());
                projectDto.setProjectCycle(project.getProCycle());
                projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
                projectDto.setProjectEnrollment(project.getProEnrollment());
                projectDto.setProjectDescription(project.getProDescription());
                projectDto.setProjectRequest(project.getProRequest());
                projectDto.setProjectState(project.getProState());
                projectsInView.add(projectDto);
            });
        }
        return projectsInView;
    }

    @Override
    public List<ProjectDto> getProjectsByType(String type) {
        List<Project> projectsInDB = projectMapper.findByType(type);
        List<ProjectDto> projectsInView = new ArrayList<>();

        if (projectsInDB != null) {
            projectsInDB.forEach(project -> {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setProjectId(project.getId());
                projectDto.setCompanyId(project.getCompanyId());
                projectDto.setCompanyName(project.getCompanyName());
                projectDto.setProjectName(project.getProName());
                projectDto.setProjectMoney(project.getProMoney());
                projectDto.setProjectType(project.getProType());
                projectDto.setProjectCycle(project.getProCycle());
                projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
                projectDto.setProjectEnrollment(project.getProEnrollment());
                projectDto.setProjectDescription(project.getProDescription());
                projectDto.setProjectRequest(project.getProRequest());
                projectDto.setProjectState(project.getProState());
                projectsInView.add(projectDto);
            });
        }
        return projectsInView;
    }

    @Override
    public List<ProjectDto> getProjectsByMoney(double minMoney, double maxMoney) {
        List<Project> projectsInDB = projectMapper.findByMoney(minMoney, maxMoney);
        List<ProjectDto> projectsInView = new ArrayList<>();
        if (projectsInDB != null) {
            projectsInDB.forEach(project -> {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setProjectId(project.getId());
                projectDto.setCompanyId(project.getCompanyId());
                projectDto.setCompanyName(project.getCompanyName());
                projectDto.setProjectName(project.getProName());
                projectDto.setProjectMoney(project.getProMoney());
                projectDto.setProjectType(project.getProType());
                projectDto.setProjectCycle(project.getProCycle());
                projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
                projectDto.setProjectEnrollment(project.getProEnrollment());
                projectDto.setProjectDescription(project.getProDescription());
                projectDto.setProjectRequest(project.getProRequest());
                projectDto.setProjectState(project.getProState());
                projectsInView.add(projectDto);
            });
        }
        return projectsInView;
    }

    @Override
    public List<ProjectDto> getProjectsByCycle(int minCycle, int maxCycle) {
        List<Project> projectsInDB = projectMapper.findByCycle(minCycle, maxCycle);
        List<ProjectDto> projectsInView = new ArrayList<>();
        if (projectsInDB != null) {
            projectsInDB.forEach(project -> {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setProjectId(project.getId());
                projectDto.setCompanyId(project.getCompanyId());
                projectDto.setCompanyName(project.getCompanyName());
                projectDto.setProjectName(project.getProName());
                projectDto.setProjectMoney(project.getProMoney());
                projectDto.setProjectType(project.getProType());
                projectDto.setProjectCycle(project.getProCycle());
                projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
                projectDto.setProjectEnrollment(project.getProEnrollment());
                projectDto.setProjectDescription(project.getProDescription());
                projectDto.setProjectRequest(project.getProRequest());
                projectDto.setProjectState(project.getProState());
                projectsInView.add(projectDto);
            });
        }
        return projectsInView;
    }

    @Override
    public List<ProjectDto> orderForProjects(int orderCode) {
        List<Project> projectsInDB = new ArrayList<>();
        List<ProjectDto> projectsInView = new ArrayList<>();
        switch (orderCode) {
            case 31:
                projectsInDB = projectMapper.orderByPubTime();
                break;
            case 32:
                projectsInDB = projectMapper.orderByMoney();
                break;
            case 33:
                projectsInDB = projectMapper.projectsNoneApplicant();
                break;
        }
        if (projectsInDB != null) {
            projectsInDB.forEach(project -> {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setProjectId(project.getId());
                projectDto.setCompanyId(project.getCompanyId());
                projectDto.setCompanyName(project.getCompanyName());
                projectDto.setProjectName(project.getProName());
                projectDto.setProjectMoney(project.getProMoney());
                projectDto.setProjectType(project.getProType());
                projectDto.setProjectCycle(project.getProCycle());
                projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
                projectDto.setProjectEnrollment(project.getProEnrollment());
                projectDto.setProjectDescription(project.getProDescription());
                projectDto.setProjectRequest(project.getProRequest());
                projectDto.setProjectState(project.getProState());
                projectsInView.add(projectDto);
            });
        }
        return projectsInView;
    }

    @Override
    public List<ProjectDto> getBySubscribe(int userId) {
        List<Subscribe> subscribes = subscribeMapper.findSubscribeByUserId(userId);
        List<Project> projectsInDB = new ArrayList<>();
        List<ProjectDto> projectsInView = new ArrayList<>();

        subscribes.forEach(subscribe -> {
            List<Project> projects = projectMapper.findBySubscribe(subscribe.getSubSpot(), subscribe.getSubType(), subscribe.getSubMinPay(), subscribe.getSubMaxPay());
            if (projects != null) {
                projectsInDB.addAll(projects);
            }
        });
        projectsInDB.forEach(project -> {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectId(project.getId());
            projectDto.setCompanyId(project.getCompanyId());
            projectDto.setCompanyName(project.getCompanyName());
            projectDto.setProjectName(project.getProName());
            projectDto.setProjectMoney(project.getProMoney());
            projectDto.setProjectType(project.getProType());
            projectDto.setProjectCycle(project.getProCycle());
            projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
            projectDto.setProjectEnrollment(project.getProEnrollment());
            projectDto.setProjectDescription(project.getProDescription());
            projectDto.setProjectRequest(project.getProRequest());
            projectDto.setProjectState(project.getProState());
            projectsInView.add(projectDto);
        });
        return projectsInView;
    }

    @Override
    public List<ProjectDto> getProjectsByConditions(String proName, int typeCode, double minProMoney, double maxProMoney, int maxProCycle) {
        String proType = null;
        switch (typeCode) {
            case 1:
                proType = ConstantHolder.PRO_TYPE_ONE;
                break;
            case 2:
                proType = ConstantHolder.PRO_TYPE_TWO;
                break;
            case 3:
                proType = ConstantHolder.PRO_TYPE_THREE;
                break;
            case 4:
                proType = ConstantHolder.PRO_TYPE_FOUR;
                break;
            case 5:
                proType = ConstantHolder.PRO_TYPE_FIVE;
                break;
            case 6:
                proType = ConstantHolder.PRO_TYPE_SIX;
                break;
            case 7:
                proType = ConstantHolder.PRO_TYPE_SEVEN;
                break;
        }

        List<Project> projectsInDB = projectMapper.findByConditions(proName, proType, minProMoney, maxProMoney, maxProCycle);
        List<ProjectDto> projectsInView = new ArrayList<>();
        projectsInDB.forEach(project -> {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectId(project.getId());
            projectDto.setCompanyId(project.getCompanyId());
            projectDto.setCompanyName(project.getCompanyName());
            projectDto.setProjectName(project.getProName());
            projectDto.setProjectMoney(project.getProMoney());
            projectDto.setProjectType(project.getProType());
            projectDto.setProjectCycle(project.getProCycle());
            projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
            projectDto.setProjectEnrollment(project.getProEnrollment());
            projectDto.setProjectDescription(project.getProDescription());
            projectDto.setProjectRequest(project.getProRequest());
            projectDto.setProjectState(project.getProState());
            projectsInView.add(projectDto);
        });
        return projectsInView;
    }

    @Override
    public ProjectDto getProjectById(int proId) {
        Project project = projectMapper.findByProId(proId);
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(project.getId());
        projectDto.setCompanyId(project.getCompanyId());
        projectDto.setCompanyName(project.getCompanyName());
        projectDto.setProjectName(project.getProName());
        projectDto.setProjectMoney(project.getProMoney());
        projectDto.setProjectType(project.getProType());
        projectDto.setProjectCycle(project.getProCycle());
        projectDto.setProjectPubTime(TimeFormatUtil.longToDate(project.getProPubTime()));
        projectDto.setProjectEnrollment(project.getProEnrollment());
        projectDto.setProjectDescription(project.getProDescription());
        projectDto.setProjectRequest(project.getProRequest());
        projectDto.setProjectState(project.getProState());

        return projectDto;
    }

    @Override
    public Boolean applyForProject(int userId, int proId) {
        Resume resume = resumeMapper.findByUserId(userId);
        Company company = companyMapper.findByProId(proId);
        Apply apply = new Apply(userId, proId, company.getId(), resume.getId());
        int proEnrolment = projectMapper.selectProjectEnrollment(proId);
        projectMapper.insertProEnrollment(proId, ++proEnrolment);
        try {
            applyMapper.save(apply);
            return true;
        } catch (Exception e) {
            logger.error("Saving apply error", e);
        }
        return false;
    }

    @Override
    public List<ProjectDto> selectProjects(int userId, int selectCode) {
        List<Project> projectInDB = new ArrayList<>();
        List<ProjectDto> projectInView = new ArrayList<>();
        switch (selectCode) {
            case 0:
                projectInDB = projectMapper.projectsAppliedAndParticipated(userId);
                break;
            case 1:
                projectInDB = projectMapper.projectsApplied(userId);
                break;
            case 2:
                projectInDB = projectMapper.projectsParticipated(userId);
                break;
        }
        projectInDB.forEach(project -> {
            ProjectDto projectDto = new ProjectDto(project);
            projectInView.add(projectDto);
        });
        return projectInView;
    }

}

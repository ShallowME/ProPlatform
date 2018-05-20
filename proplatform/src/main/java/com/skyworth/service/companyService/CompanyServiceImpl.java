package com.skyworth.service.companyService;

import com.skyworth.dto.*;
import com.skyworth.mapper.*;
import com.skyworth.model.*;
import com.skyworth.utils.TimeFormatUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = Logger.getLogger(CompanyService.class);

    @Autowired
    private CompanyMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private StageMapper stageMapper;

    @Autowired
    private TargetMapper targetMapper;

    @Autowired
    private WorkFileMapper workFileMapper;

    @Autowired
    private InviteMapper inviteMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PatentMapper patentMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private SubscribeMapper subscribeMapper;

    @Override
    public boolean register(CompanyDto companyDto) {
        if (checkCompanyExist(companyDto.getCompanyName())) {
            return false;
        }
        try {
            Company company = new Company();
            company.setCompanyName(companyDto.getCompanyName());
            company.setCompanyPhoneNum(companyDto.getPhoneNum());
            company.setCompanyPassword(companyDto.getPassword());
            mapper.save(company);
        } catch (DuplicateKeyException e) {
            logger.error("插入失败",e);
            return false;
        }
        return true;
    }

    @Override
    public Company findByCompanyId(int companyId) {
        return mapper.fingByCompanyId(companyId);
    }

    @Override
    public boolean checkCompanyExist(String companyName) {
        return mapper.countByName(companyName) > 0;
    }

    @Override
    public Company getByCompanyNameAndPassword(String companyName, String password) {
        return mapper.findByCompanyNameAndPassword(companyName,password);
    }

    @Override
    public Company getByPhoneNumAndPassword(String phoneNum, String password) {
        return mapper.findByPhoneNumAndPassword(phoneNum, password);
    }

    @Override
    public Company getByCompanyName(String companyName) {
        return mapper.findByCompanyName(companyName);
    }

    @Override
    public Company getByPhoneNum(String phoneNum) {
        return mapper.findByCompanyPhoneNum(phoneNum);
    }

    @Override
    public void updatePasswordByPhoneNum(String companyPhoneNum, String newPassword) {
        try {
            mapper.updateCompanyPasswordByPhoneNum( companyPhoneNum, newPassword);
        } catch (Exception e) {
            logger.error("Updating password failed", e);
        }
    }

    @Override
    public void updatePasswordById(int companyId, String newPassword) {
        try {
            mapper.updateCompanyPasswordById(companyId, newPassword);
        } catch (Exception e) {
            logger.error("Updating password failed",e);

        }
    }


    @Override
    public boolean setInfo(CompanyInfo info) {
        try {

            if (companyInfoMapper.findInfo(info.getCompanyId()) == null) {
                companyInfoMapper.save(info);
            } else {
                companyInfoMapper.updateInfo(info);
            }

        } catch (Exception e) {
            logger.error("插入失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInfo(CompanyInfo info) {
        try {
            if (companyInfoMapper.findInfo(info.getCompanyId()) != null) {
                companyInfoMapper.updateInfo(info);
            } else {
                companyInfoMapper.save(info);
            }
        } catch (Exception e) {
            logger.error("更新失败",e);
            return false;
        }
        return true;
    }

    @Override
    public CompanyInfo getInfo(int companyId) {
        return companyInfoMapper.findInfo(companyId);
    }

    @Override
    public boolean removeInfo(int companyId) {
        try {
            companyInfoMapper.delete(companyId);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public void saveProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setCompanyId(projectDto.getCompanyId());
        project.setCompanyName(projectDto.getCompanyName());
        project.setProName(projectDto.getProjectName());
        project.setProMoney(projectDto.getProjectMoney());
        project.setProType(projectDto.getProjectType());
        project.setProCycle(projectDto.getProjectCycle());
        project.setProPubTime(TimeFormatUtil.dateToLong(projectDto.getProjectPubTime()));
        project.setProEnrollment(projectDto.getProjectEnrollment());
        project.setProDescription(projectDto.getProjectDescription());
        project.setProRequest(projectDto.getProjectRequest());
        project.setProState(projectDto.getProjectState());
        try {
            projectMapper.save(project);
        } catch (Exception e) {
            logger.error("插入失败",e);
        }
    }

    @Override
    public void saveProject(Project project) {
        try {
            projectMapper.save(project);
        }catch (Exception e){
            logger.error("插入失败",e);
        }
    }

    @Override
    public Project getProjectByProNameAndCompanyId(int proCompanyId, String projectName) {
        return projectMapper.findByProNameAndCompanyId(proCompanyId, projectName);
    }

    @Override
    public void updateProject(ProjectDto projectDto) {
        Project project = new Project();
        try {
            projectMapper.update(project);
        } catch (Exception e) {
            logger.error("更新失败", e);
        }
    }

    @Override
    public boolean removeProject(int id) {
        try {
            projectMapper.delete(id);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public List<ProjectDto> checkProjects(int companyId) {
        List<Project> projectsInDB = projectMapper.findByCompanyId(companyId);
        List<ProjectDto> projectsInView = new ArrayList<>();
        projectsInDB.forEach(project -> {
            ProjectDto projectDto = new ProjectDto(project);
            projectsInView.add(projectDto);
        });
        return projectsInView;
    }

    @Override
    public void saveStage(StageDto stageDto) {
        Stage stage = new Stage();
        stage.setProId(stageDto.getProId());
        stage.setStageNum(stageDto.getStageNum());
        stage.setStageStartTime(TimeFormatUtil.dateToLong(stageDto.getStageStartTime()));
        stage.setStageEndTime(TimeFormatUtil.dateToLong(stageDto.getStageEndTime()));
        stage.setStageSettleRequest(stageDto.getStageSettleRequest());
        try {
            stageMapper.save(stage);
        } catch (Exception e) {
            logger.error("插入失败", e);
        }
    }

    @Override
    public void updateStage(StageDto stageDto) {
        Stage stage = new Stage();
        stage.setId(stageDto.getStageId());
        stage.setProId(stageDto.getProId());
        stage.setStageNum(stageDto.getStageNum());
        stage.setStageStartTime(TimeFormatUtil.dateToLong(stageDto.getStageStartTime()));
        stage.setStageEndTime(TimeFormatUtil.dateToLong(stageDto.getStageEndTime()));
        stage.setStageSettleRequest(stageDto.getStageSettleRequest());
        try {
            stageMapper.update(stage);
        } catch (Exception e) {
            logger.error("更新失败",e);
        }
    }


    @Override
    public boolean removeStage(int id) {
        try {
            stageMapper.delete(id);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public List<Stage> getAllStage(int proId) {
        return stageMapper.findStageByProId(proId);
    }

    @Override
    public Stage getStage(int id) {
        return stageMapper.findStageById(id);
    }

    @Override
    public void saveTarget(TargetDto targetDto) {
        Target target = new Target();
        target.setStageId(targetDto.getStageId());
        target.setTargetDeadline(TimeFormatUtil.dateToLong(targetDto.getTargetDeadline()));
        target.setTargetDetail(targetDto.getTargetDetail());
        try {
            targetMapper.save(target);
        } catch (Exception e) {
            logger.error("插入失败",e);
        }
    }

    @Override
    public void saveTarget(Target target) {
        try {
            targetMapper.save(target);
        } catch (Exception e) {
            logger.error("插入失败",e);
        }
    }

    @Override
    public void updateTarget(TargetDto targetDto) {
        Target target = new Target();
        target.setId(targetDto.getTargetId());
        target.setStageId(targetDto.getStageId());
        target.setTargetDeadline(TimeFormatUtil.dateToLong(targetDto.getTargetDeadline()));
        target.setTargetDetail(targetDto.getTargetDetail());
        try {
            targetMapper.update(target);
        } catch (Exception e) {
            logger.error("更新失败",e);
        }
    }

    @Override
    public void updateTarget(Target target) {
        try {
            targetMapper.update(target);
        } catch (Exception e) {
            logger.error("更新失败",e);
        }
    }

    @Override
    public boolean removeTarget(int targetId) {
        try {
            targetMapper.delete(targetId);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeTargetByBatch(List<Integer> list) {
        try {
            targetMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public List<Target> getTargetByStageId(int stageId) {
        return targetMapper.findByStageId(stageId);
    }

    @Override
    public Target getTargetById(int targetId) {
        return targetMapper.findById(targetId);
    }

    @Override
    public Integer allTargetsOneStage(int stageId) {
        return targetMapper.allTargetsOneStage(stageId);
    }

    @Override
    public Integer completedTargetsOneStage(int stageId) {
        return targetMapper.completedTargetsOneStage(stageId);
    }

    @Override
    public boolean saveFile(WorkFile workFile) {
        try {
            workFileMapper.save(workFile);
        } catch (Exception e) {
            logger.error("插入失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFile(WorkFile workFile) {
        try {
            workFileMapper.update(workFile);
        } catch (Exception e) {
            logger.error("更新失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeFile(int fileId) {
        try {
            workFileMapper.delete(fileId);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeFileByBatch(List<Integer> list) {
        try {
            workFileMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public WorkFile getFileById(int fileId) {
        return workFileMapper.findById(fileId);
    }

    @Override
    public boolean saveInvite(InviteDto inviteDto) {
        Invite invite = new Invite();
        invite.setProId(inviteDto.getProId());
        invite.setCompanyId(inviteDto.getCompanyId());
        invite.setResumeId(inviteDto.getResumeId());

        User user = userMapper.findByResumeId(inviteDto.getResumeId());
        invite.setInviteeId(user.getId());
        try {
            inviteMapper.save(invite);
        } catch (Exception e) {
            logger.error("插入失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInvite(Invite invite) {
        try {
            inviteMapper.update(invite);
        } catch (Exception e) {
            logger.error("更新失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeInvite(int id) {
        try {
            inviteMapper.delete(id);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeInviteByBatch(List<Integer> list) {
        try {
            inviteMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public List<Invite> getInviteByCompany(int companyId) {
        return inviteMapper.findInvitesByCompanyId(companyId);
    }

    @Override
    public List<Invite> getInviteByUser(int resumeId) {
        return inviteMapper.findInvitesByResumeId(resumeId);
    }

    /*保留*/
    @Override
    public List<Invite> getInviteByProId(int proId) {
        return inviteMapper.findInvitesByProId(proId);
    }

    @Override
    public boolean saveMessage(Message message) {
        try {
            messageMapper.save(message);
        } catch (Exception e) {
            logger.error("插入失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeMessage(int mesId) {
        try {
            messageMapper.delete(mesId);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeMessageByBatch(List<Integer> list) {
        try {
            messageMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败",e);
            return false;
        }
        return true;
    }

    @Override
    public List<Message> getMessageByCompany(int companyId) {
        return messageMapper.findByCompanyId(companyId);
    }

    @Override
    public List<Message> getMessageByUser(int userId) {
        return messageMapper.findByUserId(userId);
    }

    @Override
    public Message getMessage(int id) {
        return messageMapper.findById(id);
    }

    @Override
    public Set<Role> getAllRoles(int companyId) {
        return mapper.getAllRoles(companyId);
    }

    @Override
    public Set<Permission> getAllPermissions(int roleId) {
        return roleMapper.getAllPermissions(roleId);
    }


    @Override
    public List<ResumeDto> getApplicationsByProjectId(int projectId) {
        List<ResumeDto> resumeInView = new ArrayList<>();
        List<Apply> applicationsInDB = applyMapper.findByCompanyId(projectId);
        if (applicationsInDB != null){
            applicationsInDB.forEach(apply -> {
                Resume resume = resumeMapper.findById(apply.getResumeId());
                ResumeDto resumeDto = new ResumeDto(resume);
                resumeDto.setSelectMark(apply.getApplyMark());
                resumeDto.setResumeCompletedProjects(applyMapper.completedApplications(resume.getId())
                        + inviteMapper.completedInvitations(resume.getId()));
                resumeInView.add(resumeDto);
            });
        }
        return resumeInView;
    }

    @Override
    public List<ResumeDto> getResumesByConditions(String resumeProfession, String resumeProfessionType, String resumeProvince) {
        List<Resume> resumesInDB = resumeMapper.findResumesByConditions(resumeProfession, resumeProfessionType, resumeProvince);
        List<ResumeDto> resumesInView = new ArrayList<>();
        resumesInDB.forEach(resume -> {
            ResumeDto resumeDto = new ResumeDto(resume);
            resumeDto.setResumeCompletedProjects(applyMapper.completedApplications(resume.getId()));
            resumesInView.add(resumeDto);
        });
        return resumesInView;
    }

    @Override
    public List<StageDto> getStagesByProId(int projectId) {
        List<Stage> stagesInDB = stageMapper.findStagesByProId(projectId);
        List<StageDto> stagesInView = new ArrayList<>();

        stagesInDB.forEach(stage -> {
            StageDto stageDto = new StageDto(stage);
            stageDto.setTargets(targetMapper.findByStageId(stage.getId()));
            float completedTargets = targetMapper.completedTargetsOneStage(stage.getId());
            float allTargets = targetMapper.allTargetsOneStage(stage.getId());
            stageDto.setStageSpeed(completedTargets / allTargets);
            stagesInView.add(stageDto);
        });
        return stagesInView;
    }

    @Override
    public boolean targetCompleted(int targetId) {
        try {
            targetMapper.targetCompleted(targetId);
        }catch (Exception e){
            logger.error("更新失败", e);
            return false;
        }
        return true;
    }


}

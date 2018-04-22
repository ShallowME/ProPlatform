package com.skyworth.service.companyService;

import com.skyworth.mapper.*;
import com.skyworth.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = Logger.getLogger(CompanyService.class);

    @Autowired
    private CompanyMapper mapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private StageMapper stageMapper;

    @Autowired
    private TargetMapper targetMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private InviteMapper inviteMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean register(Company company) {
        if (checkCompanyExist(company.getCompanyName())) {
            return false;
        }
        try {
            mapper.save(company);
        } catch (DuplicateKeyException e) {
            logger.error("插入失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkCompanyExist(String companyName) {
        return mapper.countByName(companyName) > 0;
    }

    @Override
    public Company getByCompanyNameAndPassword(String companyName, String password) {
        return mapper.findByCompanyNameAndPassword(companyName, password);
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
    public boolean updatePassword(String companyName, String newPassword) {
        try {
            mapper.updatePassword(companyName, newPassword);
        } catch (Exception e) {
            logger.error("更新失败", e);
            return false;
        }
        return true;
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
            logger.error("插入失败", e);
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
            logger.error("更新失败", e);
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
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveProject(Project project) {
        try {
            projectMapper.save(project);
        } catch (Exception e) {
            logger.error("插入失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProject(Project project) {
        try {
            projectMapper.update(project);
        } catch (Exception e) {
            logger.error("更新失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeProject(int id) {
        try {
            projectMapper.delete(id);
        } catch (Exception e) {
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public List<Project> checkProjects(int companyId) {
        return projectMapper.findByCompanyId(companyId);
    }

    @Override
    public boolean saveStage(Stage stage) {
        try {
            stageMapper.save(stage);
        } catch (Exception e) {
            logger.error("插入失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStage(Stage stage) {
        try {
            stageMapper.update(stage);
        } catch (Exception e) {
            logger.error("更新失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeStage(int id) {
        try {
            stageMapper.delete(id);
        } catch (Exception e) {
            logger.error("删除失败", e);
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
    public boolean saveTarget(Target target) {
        try {
            targetMapper.save(target);
        } catch (Exception e) {
            logger.error("插入失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTarget(Target target) {
        try {
            targetMapper.update(target);
        } catch (Exception e) {
            logger.error("更新失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeTarget(int targetId) {
        try {
            targetMapper.delete(targetId);
        } catch (Exception e) {
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeTargetByBatch(List<Integer> list) {
        try {
            targetMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败", e);
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
    public boolean saveFile(File file) {
        try {
            fileMapper.save(file);
        } catch (Exception e) {
            logger.error("插入失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFile(File file) {
        try {
            fileMapper.update(file);
        } catch (Exception e) {
            logger.error("更新失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeFile(int fileId) {
        try {
            fileMapper.delete(fileId);
        } catch (Exception e) {
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeFileByBatch(List<Integer> list) {
        try {
            fileMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public File getFileById(int fileId) {
        return fileMapper.findById(fileId);
    }

    @Override
    public List<File> getFilesByCompanyId(int companyId) {
        return fileMapper.findFileByCompanyId(companyId);
    }

    @Override
    public List<File> getFilesByStageId(int stageId) {
        return fileMapper.findFileByStageId(stageId);
    }

    @Override
    public boolean saveInvite(Invite invite) {
        try {
            inviteMapper.save(invite);
        } catch (Exception e) {
            logger.error("插入失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInvite(Invite invite) {
        try {
            inviteMapper.update(invite);
        } catch (Exception e) {
            logger.error("更新失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeInvite(int id) {
        try {
            inviteMapper.delete(id);
        } catch (Exception e) {
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeInviteByBatch(List<Integer> list) {
        try {
            inviteMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败", e);
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
            logger.error("插入失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeMessage(int mesId) {
        try {
            messageMapper.delete(mesId);
        } catch (Exception e) {
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeMessageByBatch(List<Integer> list) {
        try {
            messageMapper.deleteByBatch(list);
        } catch (Exception e) {
            logger.error("删除失败", e);
            return false;
        }
        return true;
    }

    @Override
    public List<Message> getMessageByCompany(int companyId) {
        return messageMapper.findByCompanyId(companyId);
    }

    @Override
    public List<Message> getOfflineMessages(int companyId) {
        return messageMapper.findOfflineMessagesByCompanyId(companyId);
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
    public List<Message> getMessageByMesState(int companyId, int mesState) {
        return messageMapper.findMessageByCompanyIdAndMesState(companyId, mesState);
    }

    @Override
    public boolean modifyMessageState(List<Integer> ids, Integer changeCode) {
        if (ids.size() == 0 || changeCode == null) {
            return false;
        }
        try {
            messageMapper.modifyStateByBatch(ids, changeCode);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;

    }

    public Set<Role> getAllRoles ( int companyId){
        return mapper.getAllRoles(companyId);
    }

    @Override
    public Set<Permission> getAllPermissions ( int roleId){
        return roleMapper.getAllPermissions(roleId);

    }
}

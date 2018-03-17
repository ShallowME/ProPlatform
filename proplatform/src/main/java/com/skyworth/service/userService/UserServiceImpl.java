package com.skyworth.service.userService;

import com.skyworth.mapper.*;
import com.skyworth.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Shallow on 2018/3/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private PatentMapper patentMapper;

    @Autowired
    private SubscribeMapper subscribeMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private MessageMapper messageMapper;

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    @Override
    public boolean register(User user) {
        if (checkUserExist(user.getUserName())) {
            return false;
        }
        try {
            userMapper.save(user);
        } catch (DuplicateKeyException e) {
//            e.printStackTrace();
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserExist(String username) {
        return userMapper.countByName(username) >= 1;
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
    public boolean updatePassword(String username, String newPassword) {
        try {
            userMapper.updatePassword(username, newPassword);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean setInfo(UserInfo info) {
        try {
            userInfoMapper.save(info);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInfo(UserInfo info) {
        try {
            userInfoMapper.update(info);
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean setResume(Resume resume) {
        try {
            resumeMapper.save(resume);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateResume(Resume resume) {
        try {
            resumeMapper.update(resume);
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean setPatent(Patent patent) {
        try {
            patentMapper.save(patent);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePatent(Patent patent) {
        try {
            patentMapper.update(patent);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removePatent(int patentId) {
        try {
            patentMapper.delete(patentId);
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSubscribe(Subscribe subscribe) {
        try {
            subscribeMapper.update(subscribe);
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeSubscribeByBatch(List<Integer> list) {
        try {
            subscribeMapper.deleteByBatch(list);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveFile(File file) {
        try {
            fileMapper.save(file);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFile(File file) {
        try {
            fileMapper.update(file);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public File getFileById(int fileId) {
        return fileMapper.findById(fileId);
    }

    @Override
    public List<File> getFilesByUserId(int userId) {
        return fileMapper.findFileByUserId(userId);
    }

    @Override
    public boolean removeFile(int fileId) {
        try {
            fileMapper.delete(fileId);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeFileByBatch(List<Integer> list) {
        try {
            fileMapper.deleteByBatch(list);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean saveApply(Apply apply) {
        try {
            applyMapper.save(apply);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateApply(Apply apply) {
        try {
            applyMapper.update(apply);
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
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
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeMessageByBatch(List<Integer> list) {
        try {
            messageMapper.deleteByBatch(list);
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

}
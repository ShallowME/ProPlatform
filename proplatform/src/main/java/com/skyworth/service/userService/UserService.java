package com.skyworth.service.userService;

import com.skyworth.model.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Shallow on 2018/3/6.
 */
public interface UserService {

    boolean register(User user);

    boolean checkUserExist(String username);

    User getByUsernameAndPassword(User user);

    User getByUsername(String username);

    User getByPhoneNum(String phoneNum);

    boolean updatePassword(String username, String newPassword);

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

    boolean saveFile(File file);

    boolean updateFile(File file);

    File getFileById(int fileId);

    List<File> getFilesByUserId(int userId);

    boolean removeFile(int fileId);

    boolean removeFileByBatch(List<Integer> list);

    boolean saveApply(Apply apply);

    boolean updateApply(Apply apply);

    List<Apply> getApplyByUserId(int userId);

    Apply getApplyById(int id);

    boolean saveMessage(Message message);

    List<Message> getMessageByUserId(int userId);

    Message getMessageById(int id);

    boolean removeMessage(int mesId);

    boolean removeMessageByBatch(List<Integer> list);

    boolean modifyMessageState(List<Integer> list, Integer changCode);

    List<Message> getOfflineMessages(int userId);

    List<Message> getMessageByMesState(int userId, int mesState);


    Set<Role> getAllRoles(int userId);

    Set<Permission> getAllPermissions(int roleId);

}

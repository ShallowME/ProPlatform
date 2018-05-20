package com.skyworth.mapper;

import com.skyworth.model.Resume;
import com.skyworth.model.Role;
import com.skyworth.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import java.util.Set;


/**
 * Created by Shallow on 2018/3/6.
 */
@Repository
public interface UserMapper {
    void save (User user)throws DuplicateKeyException;

    User fineByUserId(int id);

    User findByUsernameAndPassword(User user);

    User findByUsername(String userName);

    User findByPhoneNum(String phoneNum);

    int countByName(@Param("userName") String userName);

    int countByPhoneNum(@Param("phoneNum") String phoneNum);

    long countUser();

    void updateUserPasswordByPhoneNum(@Param("userPhoneNum") String userPhoneNum, @Param("newPassword") String newPassword);

    void updateUserPasswordById(@Param("userId") int userId, @Param("newPassword") String newPassword);

    Set<Role> getAllRoles(@Param("userId") int userId);

    User findByResumeId(@Param("resumeId") int resumeId);
}

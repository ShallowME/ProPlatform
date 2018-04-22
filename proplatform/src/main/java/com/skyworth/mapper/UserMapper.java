package com.skyworth.mapper;

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

    User findByUsernameAndPassword(User user);

    User findByUsername(String userName);

    User findByPhoneNum(String phoneNum);

    int countByName(@Param("userName") String userName);

    int countByPhoneNum(@Param("phoneNum") String phoneNum);

    long countUser();

    void updatePassword(@Param("userName") String userName, @Param("newPassword") String newPassword);

    Set<Role> getAllRoles(@Param("userId") int userId);

}

package com.skyworth.mapper;

import com.skyworth.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {

    void save(UserInfo info);

    void update(UserInfo newInfo);

    void delete(@Param("userId") int userId);

    UserInfo findUserInfo(@Param("userId") int userId);
}

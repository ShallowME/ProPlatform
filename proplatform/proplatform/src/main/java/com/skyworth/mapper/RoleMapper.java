package com.skyworth.mapper;

import com.skyworth.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    void save(Role role)throws DuplicateKeyException;

    void update(Role role);

    Role findRole(@Param("roleName") String roleName);

}

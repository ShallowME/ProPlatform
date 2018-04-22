package com.skyworth.mapper;

import com.skyworth.model.Permission;
import com.skyworth.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleMapper {
    void save(Role role)throws DuplicateKeyException;

    void update(Role role);

    Role findRole(@Param("roleName") String roleName);

    Set<Permission> getAllPermissions(@Param("roleId") int roleId);
}

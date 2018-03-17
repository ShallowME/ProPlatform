package com.skyworth.mapper;

import com.skyworth.model.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    void save(RolePermission rolePermission)throws DuplicateKeyException;

    void update(@Param("roleId")int roleId,@Param("perId")int perId,@Param("newPerId")int newPerId)throws DataIntegrityViolationException;

    List<RolePermission> findRolePermissionByRoleId(@Param("roleId") int roleId);

    List<RolePermission> findRolePermissionByPermissionId(@Param("permissionId") int permissionId);
}

package com.skyworth.mapper;

import com.skyworth.model.CompanyRole;
import com.skyworth.model.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRoleMapper {
    void saveUserRole(UserRole userRole) throws DuplicateKeyException;

    void saveCompanyRole(CompanyRole companyRole) throws DuplicateKeyException;

    void updateByUserId(@Param("userRole") UserRole userRole, @Param("newRoleId") int newRoleId) throws DataIntegrityViolationException;

    void updateByCompanyId(@Param("companyRole") CompanyRole companyRole, @Param("newRoleId") int newRoleId) throws DataIntegrityViolationException;

    List<UserRole> findUserRoleById(@Param("userId") int userId);

    List<CompanyRole> findCompanyRoleById(@Param("companyId") int companyId);

}

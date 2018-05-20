package com.skyworth.mapper;

import com.skyworth.model.Company;
import com.skyworth.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CompanyMapper {
    void save(Company company)throws DuplicateKeyException;

    long countByName(@Param("companyName") String companyName);

    Company fingByCompanyId(@Param("companyId") int companyId);

    Company findByCompanyName(@Param("companyName") String companyName);

    Company findByCompanyPhoneNum(@Param("phoneNum") String phoneNum);

    Company findByCompanyNameAndPassword(@Param("companyName") String companyName, @Param("password") String password);

    Company findByPhoneNumAndPassword(@Param("phoneNum") String phoneNum, @Param("password") String password);

    void updateCompanyPasswordByPhoneNum(@Param("companyPhoneNum") String companyPhoneNum, @Param("newPassword") String newPassword);

    void updateCompanyPasswordById(@Param("companyId") int companyId, @Param("newPassword") String newPassword);

    Set<Role> getAllRoles(@Param("companyId") int companyId);

    Company findByProId(@Param("proId") int proId);
}

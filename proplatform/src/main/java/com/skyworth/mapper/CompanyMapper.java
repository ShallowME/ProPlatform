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

    Company findByCompanyName(@Param("companyName") String companyName);

    Company findByCompanyPhoneNum(@Param("phoneNum") String phoneNum);

    Company findByCompanyNameAndPassword(@Param("companyName") String companyName, @Param("password") String password);

    Company findByPhoneNumAndPassword(@Param("phoneNum") String phoneNum, @Param("password") String password);

    void updatePassword(@Param("companyName") String companyName, @Param("newPassword") String newPassword);

    Set<Role> getAllRoles(@Param("companyId") int companyId);

}

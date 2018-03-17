package com.skyworth.mapper;

import com.skyworth.model.CompanyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyInfoMapper {
    void save(CompanyInfo info);

    void updateInfo(CompanyInfo info);

    CompanyInfo findInfo(@Param("companyId") int companyId);

    void delete(@Param("companyId")int companyId);
}

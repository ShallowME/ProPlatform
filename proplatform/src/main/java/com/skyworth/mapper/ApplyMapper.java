package com.skyworth.mapper;

import com.skyworth.model.Apply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyMapper {
    void save(Apply apply);

    void update(Apply apply);

    void delete(@Param("id") int id);

    void deleteByUser(@Param("applicantId") int userId, @Param("proId") int proId);

    List<Apply> findApplyByUserId(@Param("applicantId") int userId);

    Apply findById(@Param("id") int id);

    List<Apply> findByCompanyId(@Param("companyId") int companyId);

    List<Apply> findByProjectId(@Param("projectId") int projectId);

    Long completedApplications(@Param("resumeId") int resumeId);

}

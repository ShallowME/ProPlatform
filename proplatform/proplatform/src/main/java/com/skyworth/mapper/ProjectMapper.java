package com.skyworth.mapper;

import com.skyworth.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {
    void save(Project project);

    void saveByBatch(List<Project> projects);

    void update(Project project);

    void delete(@Param("id") int id);

    void deleteByBatch(List<Integer> list);

    long countByCompanyName(@Param("companyName") String companyName);

    long countByName(@Param("proName") String proName);

    long countByType(@Param("proType") String proType);

    List<Project> findByCompanyId(@Param("companyId") int companyId);

    List<Project> findByProName(@Param("proName") String proName);

    List<Project> findByCompanyName(@Param("companyName") String companyName);

    List<Project> findByMoney(@Param("minProMoney") double minProMoney,@Param("maxProMoney")double maxProMoney);

    List<Project> findByType(@Param("proType") String proType);

    List<Project> findByCycle(@Param("minProCycle") int minProCycle,@Param("maxProCycle") int maxProCycle);

    List<Project> findByPubTime(@Param("minProPubTime") long minProPubTime,@Param("maxProPubTime") long maxProPubTime);

}

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

    long countByState(@Param("peoState") int proState);

    Project findByProId(@Param("proId") int proId);

    Project findByProNameAndCompanyId(@Param("proCompanyId") int proCompanyId, @Param("projectName") String projectName);

    List<Project> findByCompanyId(@Param("companyId") int companyId);

    List<Project> findByProName(@Param("proName") String proName);

    List<Project> findByCompanyName(@Param("companyName") String companyName);

    List<Project> findByMoney(@Param("minProMoney") double minProMoney,@Param("maxProMoney")double maxProMoney);

    List<Project> findByType(@Param("proType") String proType);

    List<Project> findByCycle(@Param("minProCycle") int minProCycle,@Param("maxProCycle") int maxProCycle);

    List<Project> findByPubTime(@Param("minProPubTime") long minProPubTime,@Param("maxProPubTime") long maxProPubTime);

    List<Project> orderByPubTime();

    List<Project> orderByMoney();

    List<Project> projectsNoneApplicant();

    List<Project> findBySubscribe(@Param("expectedSpot") String companyLocation, @Param("expectedType") String proType, @Param("expected") double minProMoney, @Param("expected") double maxProMoney);

    List<Project> findByConditions(@Param("proName") String proName, @Param("proType")String proType, @Param("minProMoney") double minProMoney, @Param("maxProMoney") double maxProMoney, @Param("maxProCycle") int maxProCycle);

    List<Project> projectsAppliedAndParticipated(@Param("userId") int userId);

    List<Project> projectsApplied(@Param("userId") int userId);

    List<Project> projectsParticipated(@Param("userId") int userId);

    Integer selectProjectEnrollment(@Param("proId") int proId);

    void insertProEnrollment(@Param("proId") int proId, @Param("enrollment") int enrollment);
}

package com.skyworth.mapper;

import com.skyworth.model.Stage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageMapper {
    void save(Stage stage);

    void update(Stage stage);

    void delete(@Param("id") long id);

    List<Stage> findStageByProId(@Param("proId") long proId);

    Stage findStageById(@Param("id") long id);

    List<Stage> findStagesByProId(@Param("projectId") int projectId);
}

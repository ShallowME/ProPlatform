package com.skyworth.mapper;

import com.skyworth.model.Target;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetMapper {
    void save(Target target);

    void update(Target target);

    void delete(@Param("id") int id);

    void deleteByBatch(List<Integer> list);

    Target findById(@Param("id") int id);

    List<Target> findByStageId(@Param("stageId") long stageId);
}

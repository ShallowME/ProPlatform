package com.skyworth.mapper;

import com.skyworth.model.WorkFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkFileMapper {
    void save(WorkFile workFile);

    void update(WorkFile workFile);

    void updateByUser(WorkFile workFile);

    void delete(@Param("id") int id);

    void deleteByBatch(List<Integer> list);

    WorkFile findById(@Param("id")int id);

    List<WorkFile> findFileByUserId(@Param("userId") int userId);

    void deleteByUser(@Param("userId") long userId);


}

package com.skyworth.mapper;

import com.skyworth.model.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {
    void save(File file);

    void update(File file);

    void updateByUser(File file);

    void updateByStage(File file);

    void updateByCompany(File file);

    void delete(@Param("id") int id);

    void deleteByBatch(List<Integer> list);

    File findById(@Param("id")int id);

    List<File> findFileByStageId(@Param("stageId") int stageId);

    List<File> findFileByUserId(@Param("userId") int userId);

    List<File> findFileByCompanyId(@Param("companyId") int companyId);

    void deleteByUser(@Param("userId") long userId);

    void deleteByCompany(@Param("companyId") long companyId);

}

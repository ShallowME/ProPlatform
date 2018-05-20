package com.skyworth.mapper;

import com.skyworth.model.Resume;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeMapper {
    void save(Resume resume);

    void update(Resume resume);

    Resume findById(@Param("resumeId") int resumeId);

    Resume findByUserId(@Param("userId") int userId);

    int countByUserId(@Param("userId") int userId);

    void delete(@Param("id") int id);

    List<Resume> findResumesByMajor(@Param("majorType") String majorType);

    List<Resume> findResumesByLocation(@Param("location")String location);

    List<Resume> findResumesByConditions(@Param("resumeProfession") String resumeProfession, @Param("resumeProfessionType") String resumeProfessionType, @Param("resumeProvince") String resumeProvince);
}

package com.skyworth.mapper;

import com.skyworth.model.Resume;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeMapper {
    void save(Resume resume);

    void update(Resume resume);

    Resume findByUserId(@Param("userId") int userId);

    int countByUserId(@Param("userId") int userId);

    void delete(@Param("id") int id);

}

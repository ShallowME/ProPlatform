package com.skyworth.mapper;

import com.skyworth.model.Patent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatentMapper {
    void save(Patent patent);

    void update(Patent patent);

    void delete(@Param("id") int id);

    void deleteByUserId(@Param("userId") int userId);

    Patent findPatentById(@Param("id") int patentId);

    List<Patent> findPatentByUserId(@Param("userId") int userId);

    Patent findByPatentName(@Param("patentName") String patentName);

    int countByPatentName(@Param("patentName") String patentName);

    List<Patent> findByPatentOwner(@Param("patentOwner") String patentOwner);

    List<Patent> findPatentsByName(@Param("patentName") String patentName);
}

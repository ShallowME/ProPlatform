package com.skyworth.mapper;

import com.skyworth.model.Subscribe;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeMapper {
    void save(Subscribe subscribe);

    void saveByBatch(List<Subscribe> subscribes);

    void update(Subscribe subscribe);

    void delete(@Param("id") int id);

    void deleteByBatch(List<Integer> list);

    Subscribe findById(@Param("id") int id);

    List<Subscribe> findSubscribeByUserId(@Param("userId") int userId);

    List<Subscribe> findByUserIdAndType(@Param("userId") int userId, @Param("subType") String subType);

    int countByUserId(@Param("userId") int userId);

    int countByUserIdAndType(@Param("userId") int userId, @Param("subType") String subType);

}

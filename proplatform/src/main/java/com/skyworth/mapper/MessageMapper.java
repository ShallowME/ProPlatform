package com.skyworth.mapper;

import com.skyworth.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    void save(Message message);

    void delete(@Param("id") int id);

    void deleteByBatch(List<Integer> list);

    void deleteByUserId(@Param("userId") int userId);

    void deleteByCompanyId(@Param("companyId") int companyId);

    Message findById(@Param("id") int id);

    List<Message> findByUserId(@Param("userId") int userId);

    List<Message> findOfflineMessagesByUserId(@Param("userId") int userId);

    List<Message> findByCompanyId(@Param("companyId") int companyId);

    List<Message> findOfflineMessagesByCompanyId(@Param("companyId") int companyId);

    void modifyStateByBatch(@Param("list") List<Integer> ids, @Param("changeCode") int changeCode);

    List<Message> findMessageByUserIdAndMesState(@Param("userId") int userId, @Param("mesState") int mesState);

    List<Message> findMessageByCompanyIdAndMesState(@Param("companyId") int companyId, @Param("mesState") int mesState);
}

package com.skyworth.mapper;

import com.skyworth.model.Invite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteMapper {
    void save(Invite invite);

    void update(Invite invite);

    void delete(@Param("id") int id);

    void deleteByBatch(List<Integer> list);

    void deleteByCompany(@Param("companyId") int companyId, @Param("proId") int proId);

    Invite findById(@Param("id") int id);

    List<Invite> findInvitesByCompanyId(@Param("companyId") int companyId);

    List<Invite> findInvitesByResumeId(@Param("resumeId") int resumeId);

    List<Invite> findInvitesByProId(@Param("proId") int proId);
}

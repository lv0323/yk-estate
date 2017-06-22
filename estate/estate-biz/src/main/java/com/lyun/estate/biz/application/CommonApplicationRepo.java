package com.lyun.estate.biz.application;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommonApplicationRepo {

    @Insert("INSERT INTO T_COMMON_APPLICATION (TYPE, STATUS, APPLICANT_ID, APPLY_REASON, DOMAIN_ID, DOMAIN_FROM, DOMAIN_TO) " +
            " VALUES (#{type}, #{status}, #{applicantId}, #{applyReason}, #{domainId}, #{domainFrom}, #{domainTo})")
    @Options(useGeneratedKeys = true)
    int create(CommonApplicationEntity commonApplicationEntity);

    @Update("UPDATE T_COMMON_APPLICATION SET STATUS = #{status}, REVIEWER_ID = #{reviewerId}, REVIEWER_COMMENTS = #{reviewerComments}, UPDATE_TIME = NOW() WHERE ID = #{} ")
    int updateStatusById(@Param("id") long id, @Param("status") CommonApplicationEntity.Status status, @Param("reviewerId") long reviewerId, @Param("reviewerComments") String reviewerComments);

    @Select("SELECT * FROM T_COMMON_APPLICATION WHERE ID = #{id}")
    CommonApplicationEntity findOneById(@Param("id") long id);

    @SelectProvider(type = CommonApplicationSqlProvider.class, method = "findApplications")
    List<CommonApplicationEntity> findApplications(@Param("type") CommonApplicationEntity.Type type,
                                                   @Param("id") long id,
                                                   @Param("applicantId") long applicantId,
                                                   @Param("status") CommonApplicationEntity.Status status,
                                                   @Param("startTime") Date startTime,
                                                   @Param("endTime") Date endTime,
                                                   PageBounds pageBounds);
}

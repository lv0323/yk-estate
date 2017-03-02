package com.lyun.estate.biz.follow.repository;

import com.lyun.estate.biz.follow.entity.Follow;
import com.lyun.estate.biz.support.def.DomainType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowMapper {

    @Insert("insert into t_follow(target_id,domain_type, follower_id,create_time) values(#{targetId},#{domainType},#{followerId},current_timestamp)")
    int createFollow(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("followerId") long followerId);

    @Delete("delete from t_follow where target_id=#{targetId} and domain_type = #{domainType} and follower_id=#{followerId}")
    int deleteFollow(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("followerId") long followerId);

    @Select("select * from t_follow where target_id=#{targetId} and domain_type = #{domainType} and follower_id=#{followerId}")
    Map<String, Object> findFollow(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("followerId") long followerId);

    @Select("select * from t_follow where domain_type = #{domainType} and target_id=#{targetId}")
    List<Follow> getFollowers(@Param("domainType") DomainType domainType, @Param("targetId") Long targetId);
}

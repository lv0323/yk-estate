package com.lyun.estate.biz.favorite.repository;

import com.lyun.estate.biz.favorite.entity.Follow;
import com.lyun.estate.biz.spec.common.DomainType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowMapper {

    @Insert("insert into t_follow(target_id,follower_id,create_time) values(#{targetId},#{domainType},#{followerId},current_timestamp)")
    int createFollow(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("followerId") long followerId);

    @Delete("delete from t_follow where target_id=#{targetId} and domain_type = #{domainType} and follower_id=#{followerId}")
    int deleteFollow(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("followerId") long followerId);

    @Select("select * from t_follow where target_id=#{targetId} and domain_type = #{domainType} and follower_id=#{followerId}")
    Map<String, Object> findFollow(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("followerId") long followerId);

    @Select("select * from t_follow where domain_type = #{domainType} and target_id=#{targetId}")
    List<Follow> getFollowers(@Param("domainType") DomainType domainType, @Param("targetId") Long targetId);
}

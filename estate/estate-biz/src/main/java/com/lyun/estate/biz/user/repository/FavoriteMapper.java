package com.lyun.estate.biz.user.repository;

import com.lyun.estate.biz.spec.common.DomainType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface FavoriteMapper {

    @Insert("insert into t_favorite(target_id,user_id,attention_time) values(#{targetId},#{domainType},#{userId},current_timestamp)")
    int createFavorite(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("userId") long userId);

    @Delete("delete from t_favorite where target_id=#{targetId} and domain_type = #{domainType} and user_id=#{userId}")
    int deleteFavorite(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("userId") long userId);

    @Select("select * from t_favorite where target_id=#{targetId} and domain_type = #{domainType} and user_id=#{userId}")
    Map<String, Object> findFavorite(@Param("targetId") long targetId, @Param("domainType") DomainType domainType, @Param("userId") long userId);
}

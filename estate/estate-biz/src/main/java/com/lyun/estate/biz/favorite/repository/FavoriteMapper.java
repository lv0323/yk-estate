package com.lyun.estate.biz.favorite.repository;

import com.lyun.estate.biz.favorite.entity.Favorite;
import com.lyun.estate.biz.support.def.DomainType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FavoriteMapper {

    @Insert("insert into t_favorite (target_id,domain_type, type, follower_id,create_time) values(#{targetId},#{domainType}, #{type}, #{followerId},current_timestamp)")
    int createFavorite(Favorite favorite);

    @Delete("delete from t_favorite where target_id=#{targetId} and domain_type = #{domainType} and follower_id=#{followerId}")
    int deleteFavorite(@Param("targetId") long targetId, @Param("domainType") DomainType domainType,
                       @Param("followerId") long followerId);

    @Select("select * from t_favorite where target_id=#{targetId} and domain_type = #{domainType} and follower_id=#{followerId}")
    Map<String, Object> findFavorite(@Param("targetId") long targetId, @Param("domainType") DomainType domainType,
                                     @Param("followerId") long followerId);

    @Select("select * from t_favorite where domain_type = #{domainType} and target_id=#{targetId}")
    List<Favorite> getFavorites(@Param("domainType") DomainType domainType, @Param("targetId") Long targetId);
}

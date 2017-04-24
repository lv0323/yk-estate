package com.lyun.estate.biz.permission.repo;

import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.support.def.DomainType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-04-21.
 */
@Repository
public interface GrantRepo {
    @Insert("INSERT INTO t_grant (target_id, target_type, permission,category, scope, limits, grant_by_id) " +
            "VALUES (#{targetId}, #{targetType}, #{permission}, #{category}, #{scope}, #{limits}, #{grantById});")
    @Options(useGeneratedKeys = true)
    int save(Grant grant);

    @Select("SELECT * FROM t_grant WHERE target_id = #{targetId} AND target_type = #{targetType} AND category = #{category} AND is_deleted = FALSE;")
    List<Grant> findGrantsByCategory(@Param("targetId") long targetId, @Param("targetType") DomainType targetType,
                                     @Param("category") PermissionDefine.Category category);

    @Select("SELECT * FROM t_grant WHERE target_id = #{targetId} AND target_type = #{targetType} AND is_deleted = FALSE;")
    List<Grant> findGrants(@Param("targetId") long targetId, @Param("targetType") DomainType targetType);

    @Update("UPDATE t_grant SET is_deleted = TRUE, update_time =now(), update_by_id =#{operatorId} WHERE target_id =#{targetId} AND  target_type = #{targetType};")
    int delAll(@Param("targetId") long targetId, @Param("targetType") DomainType targetType,
               @Param("operatorId") long operatorId);

    @Update("UPDATE t_grant SET is_deleted = TRUE, update_time =now(), update_by_id =#{operatorId} WHERE target_id =#{targetId} AND  target_type = #{targetType} AND category = #{category};")
    int delAllOfCategory(@Param("targetId") long targetId, @Param("targetType") DomainType targetType,
                         @Param("category") PermissionDefine.Category category,
                         @Param("operatorId") long operatorId);
}

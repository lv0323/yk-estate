package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.entity.FangFollow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangFollowRepo {

    @Insert("INSERT INTO t_fang_follow (fang_id, employee_id, follow_type, content) VALUES " +
            "(#{fangId}, #{employeeId}, #{followType}, #{content});")
    @Options(useGeneratedKeys = true)
    int save(FangFollow fangFollow);

    @Select("SELECT * FROM t_fang_follow WHERE id = #{id}")
    FangFollow findOne(Long id);

    @Select("SELECT * FROM t_fang_follow WHERE fang_id = #{fangId} AND is_deleted = FALSE ORDER BY id ASC ")
    PageList<FangFollow> findByFangId(@Param("fangId") Long fangId, PageBounds pageBounds);
}

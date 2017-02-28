package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.entity.FangCheck;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangCheckRepo {

    @Insert("INSERT INTO t_fang_check (fang_id, employee_id, advantage, disadvantage)  VALUES " +
            "( #{fangId}, #{employeeId}, #{advantage}, #{disAdvantage})")
    @Options(useGeneratedKeys = true)
    int save(FangCheck fangCheck);

    @Select("select * from t_fang_check where id = #{id}")
    FangCheck findOne(Long id);

    @Select("select * from t_fang_check where fang_id =#{fangId}  and is_deleted = false order by id asc")
    PageList<FangCheck> findByFangId(@Param("fangId") Long fangId, PageBounds pageBounds);
}

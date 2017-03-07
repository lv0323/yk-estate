package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.domian.FangFollowDTO;
import com.lyun.estate.biz.fang.domian.FangFollowSelector;
import com.lyun.estate.biz.fang.entity.FangFollow;
import com.lyun.estate.biz.fang.repo.provider.FangFollowSqlProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangFollowRepo {

    @Insert("INSERT INTO t_fang_follow (fang_id,company_id, department_id, employee_id, follow_type, content) VALUES " +
            "(#{fangId}, #{companyId}, #{departmentId}, #{employeeId}, #{followType}, #{content});")
    @Options(useGeneratedKeys = true)
    int save(FangFollow fangFollow);

    @Select("SELECT * FROM t_fang_follow WHERE id = #{id}")
    FangFollow findOne(Long id);

    @SelectProvider(type = FangFollowSqlProvider.class, method = "listBySelector")
    PageList<FangFollowDTO> listBySelector(FangFollowSelector selector, PageBounds pageBounds);
}

package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.domian.FangCheckDTO;
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

    @Insert("INSERT INTO t_fang_check (fang_id,company_id, department_id, employee_id, advantage, disadvantage)  VALUES " +
            "( #{fangId},#{companyId}, #{departmentId}, #{employeeId}, #{advantage}, #{disAdvantage})")
    @Options(useGeneratedKeys = true)
    int save(FangCheck fangCheck);

    @Select("select * from t_fang_check where id = #{id}")
    FangCheck findOne(Long id);

    @Select("SELECT  fc.*,  d.name AS department_name,  e.name AS employee_name\n" +
            "FROM t_fang_check fc  LEFT JOIN t_department d ON fc.department_id = d.id  LEFT JOIN t_employee e ON fc.employee_id = e.id\n" +
            "WHERE fc.fang_id = #{fangId} AND fc.is_deleted = FALSE ORDER BY fc.id ASC")
    PageList<FangCheckDTO> findByFangId(@Param("fangId") Long fangId, PageBounds pageBounds);
}

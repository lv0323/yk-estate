package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangInfoOwnerRepo {

    @Select("SELECT fio.*, d.name as department_name, e.name as employee_name FROM t_fang_info_owner fio LEFT JOIN t_department d ON fio.department_id = d.id LEFT JOIN t_employee e ON fio.employee_id = e.id where fio.fang_id = #{fangId}")
    List<FangInfoOwnerDTO> findByFangId(Long fangId);


    @Select("SELECT fio.*, d.name as department_name, e.name as employee_name" +
            " FROM t_fang_info_owner fio LEFT JOIN t_department d ON fio.department_id = d.id LEFT JOIN t_employee e ON fio.employee_id = e.id" +
            " WHERE fio.fang_id = #{fangId} AND fio.is_deleted = FALSE ORDER BY fio.id DESC LIMIT 1")
    FangInfoOwnerDTO findLastFangInfoOwner(Long fangId);
}

package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.repo.provider.MgtFangSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangInfoOwnerRepo {


    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFangInfoOwner")
    @Options(useGeneratedKeys = true)
    int saveFangInfoOwner(FangInfoOwner fangInfoOwner);

    @Select("select * from t_fang_info_owner where id = #{id}")
    FangInfoOwner findFangInfoOwner(Long id);

    @Select("SELECT fio.*, d.name as department_name, e.name as employee_name, e.mobile, c.name as company_name FROM t_fang_info_owner fio LEFT JOIN t_department d ON fio.department_id = d.id LEFT JOIN t_employee e ON fio.employee_id = e.id " +
            "LEFT JOIN t_company c ON fio.company_id = c.id where fio.fang_id = #{fangId} AND fio.is_deleted = false")
    List<FangInfoOwnerDTO> findByFangId(Long fangId);

    @Select("SELECT fio.*, d.name as department_name, e.name as employee_name, e.mobile, c.name as company_name" +
            " FROM t_fang_info_owner fio LEFT JOIN t_department d ON fio.department_id = d.id LEFT JOIN t_employee e ON fio.employee_id = e.id LEFT JOIN t_company c ON fio.company_id = c.id " +
            " WHERE fio.fang_id = #{fangId} AND fio.is_deleted = FALSE ORDER BY fio.id DESC LIMIT 1")
    FangInfoOwnerDTO findLastFangInfoOwner(Long fangId);

    @Select("SELECT fio.*, d.name as department_name, e.name as employee_name, e.mobile, c.name as company_name FROM t_fang_info_owner fio LEFT JOIN t_department d ON fio.department_id = d.id LEFT JOIN t_employee e ON fio.employee_id = e.id " +
            "LEFT JOIN t_company c ON fio.company_id = c.id where fio.fang_id = #{fangId} order by id")
    List<FangInfoOwnerDTO> getSuccessiveInfoOwners(Long fangId);

    @Update("UPDATE t_fang_info_owner SET is_deleted = TRUE WHERE fang_id = #{fangId}")
    int deleteFangInfoOwner(Long fangId);
}

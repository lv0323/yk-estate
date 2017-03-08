package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.fang.domian.MgtFangSelector;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.repo.provider.MgtFangSqlProvider;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Repository
public interface MgtFangRepository {

    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFang")
    @Options(useGeneratedKeys = true)
    int saveFang(Fang fang);

    @Select("select * from t_fang where id = #{id}")
    Fang findFang(Long id);

    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFangExt")
    @Options(useGeneratedKeys = true)
    int saveFangExt(FangExt fangExt);

    @Select("select * from t_fang_ext where id =#{id}")
    FangExt findFangExt(Long id);

    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFangInfoOwner")
    @Options(useGeneratedKeys = true)
    int saveFangInfoOwner(FangInfoOwner fangInfoOwner);

    @Select("select * from t_fang_info_owner where id = #{id}")
    FangInfoOwner findFangInfoOwner(Long id);

    @SelectProvider(type = MgtFangSqlProvider.class, method = "listSummary")
    PageList<MgtFangSummary> listSummary(MgtFangSelector selector, PageBounds pageBounds);

    @Select("SELECT fio.*, d.name as department_name, e.name as employee_name" +
            " FROM t_fang_info_owner fio LEFT JOIN t_department d ON fio.department_id = d.id LEFT JOIN t_employee e ON fio.employee_id = e.id" +
            " WHERE fio.fang_id = #{fangId} AND fio.is_deleted = FALSE ORDER BY fio.id DESC LIMIT 1")
    FangInfoOwnerDTO findLastFangInfoOwner(Long fangId);
}

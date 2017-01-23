package com.lyun.estate.biz.company.repo;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.repo.provider.CompanyProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.sql.Date;
import java.util.List;

@Mapper
public interface CompanyRepository {

    @InsertProvider(type = CompanyProvider.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int insert(Company company);

    @Update("update t_company set locked = #{locked} where id = #{id}")
    int lock(@Param("id") Long id, @Param("locked") Boolean locked);

    @Update("update t_company set end_date = #{endDate} where id = #{id}")
    int renew(@Param("id") Long id, @Param("endDate") Date endDate);

    @Update("update t_company set name = #{name},short_name = #{shortName},license = #{license},address = #{address},introduction = #{introduction}, " +
            "update_time = CURRENT_TIMESTAMP where id = #{id}")
    int update(Company company);

    @Select("select * from t_company where id = #{id}")
    Company selectOne(Long id);

    @Select("select * from t_company")
    List<Company> select(RowBounds rowBounds);
}

package com.lyun.estate.biz.company.repo;

import com.lyun.estate.biz.company.Company;
import com.lyun.estate.biz.company.CompanyProvider;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CompanyRepository {

    @InsertProvider(type = CompanyProvider.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int insert(Company company);

    @Update("update t_company set locked = #{locked} where id = #{id}")
    int lock(@Param("id") Long id, @Param("locked") Boolean locked);
}

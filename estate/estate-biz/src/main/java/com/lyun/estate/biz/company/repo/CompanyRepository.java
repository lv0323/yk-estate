package com.lyun.estate.biz.company.repo;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.repo.provider.CompanyProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CompanyRepository {

    @InsertProvider(type = CompanyProvider.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int insert(Company company);

    @Select("SELECT * FROM t_company WHERE id = #{id}; ")
    Company findOne(Long id);

    @Select("SELECT * FROM t_company WHERE secret_key = #{secretKey};")
    Company findBySecretKey(String secretKey);

    @Update("UPDATE t_company SET boss_id = #{bossId} WHERE id = #{id}")
    int updateBossId(@Param("id") Long id, @Param("bossId") Long bossId);

    @Select("SELECT * FROM t_company WHERE type IN ('YK', 'REGIONAL_AGENT')  AND status = 'ACTIVE'")
    List<Company> findYkRaCompany();
}

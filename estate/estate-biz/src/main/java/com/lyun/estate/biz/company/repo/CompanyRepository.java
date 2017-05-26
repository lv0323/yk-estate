package com.lyun.estate.biz.company.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.CompanyDTO;
import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.repo.provider.CompanyProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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

    @Select("SELECT * FROM t_company WHERE type IN ('YK', 'REGIONAL_AGENT')  AND status = 'ACTIVE'  AND is_deleted =FALSE ")
    List<Company> findYkRaCompany();

    @SelectProvider(type = CompanyProvider.class, method = "list")
    PageList<CompanyDTO> list(@Param("cityId") Long cityId,
                              @Param("parentId") Long parentId,
                              @Param("companyType") CompanyDefine.Type companyType,
                              PageBounds pageBounds);

    @Select("select count(1) from t_company where parent_id = #{parentId} and type =#{companyType} and is_deleted = false")
    Integer countForParent(@Param("parentId") Long parentId,
                           @Param("companyType") CompanyDefine.Type companyType);

    @Update("UPDATE t_company SET name = #{name}, abbr = #{abbr}, address = #{address}, introduction = #{introduction}, " +
            "start_date =#{startDate}, end_date = #{endDate} WHERE id = #{id}")
    int updateInfo(Company company);

    @Update("UPDATE t_company SET start_date = #{startDate} ,end_date =#{endDate} WHERE id =#{companyId}")
    int updateSigningDate(@Param("companyId") Long companyId, @Param("startDate") Date startDate,
                          @Param("endDate") Date endDate);
}

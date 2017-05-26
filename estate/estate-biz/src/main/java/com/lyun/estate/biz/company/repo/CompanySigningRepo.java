package com.lyun.estate.biz.company.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.company.domain.CompanySigningDTO;
import com.lyun.estate.biz.company.entity.CompanySigning;
import org.apache.ibatis.annotations.*;

/**
 * Created by Jeffrey on 2017-05-18.
 */
public interface CompanySigningRepo {

    @Insert("INSERT INTO t_company_signing (company_id, part_a_id, years, store_count, start_date, end_date, price)\n" +
            "    VALUES (#{companyId}, #{partAId}, #{years}, #{storeCount}, #{startDate}, #{endDate}, #{price})")
    @Options(useGeneratedKeys = true)
    int create(CompanySigning companySigning);

    @Select("SELECT * FROM t_company_signing WHERE id = #{id}")
    CompanySigning findOne(Long id);

    @Select("SELECT * FROM t_company_signing WHERE company_id = #{companyId} AND is_deleted = FALSE ORDER BY id;")
    PageList<CompanySigningDTO> list(@Param("companyId") Long companyId, PageBounds pageBounds);

    @Select("SELECT * FROM t_company_signing WHERE company_id = #{companyId} ORDER BY end_date DESC LIMIT 1")
    CompanySigningDTO findLastSigning(Long companyId);

    @Update("UPDATE t_company_signing SET years = #{years}, store_count = #{storeCount}, start_date = #{startDate}, " +
            "  end_date = #{endDate}, price =#{price}, update_time = now() WHERE id = #{id}")
    int updateSigningInfo(CompanySigning signing);

    @Update("UPDATE t_company_signing SET is_deleted = TRUE , update_time = now() WHERE id = #{id}")
    int delete(Long id);
}

package com.lyun.estate.biz.company.repo;

import com.lyun.estate.biz.company.domain.CompanySigning;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
}

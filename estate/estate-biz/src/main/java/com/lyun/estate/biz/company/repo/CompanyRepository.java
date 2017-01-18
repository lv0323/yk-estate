package com.lyun.estate.biz.company.repo;

import com.lyun.estate.biz.company.Company;
import com.lyun.estate.biz.company.CompanyProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CompanyRepository {

    @InsertProvider(type = CompanyProvider.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int insert(Company company);
}

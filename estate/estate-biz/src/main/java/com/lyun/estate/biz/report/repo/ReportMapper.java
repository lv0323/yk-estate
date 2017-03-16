package com.lyun.estate.biz.report.repo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

@Mapper
@Repository
public interface ReportMapper {

    @SelectProvider(type = ReportSqlProvider.class , method = "getSql")
    @Options(fetchSize = 1000, resultSetType = ResultSetType.SCROLL_INSENSITIVE)
    @ResultType(LinkedHashMap.class)
    void report(@Param("sql") String sql, ResultHandler resultHandler);
}

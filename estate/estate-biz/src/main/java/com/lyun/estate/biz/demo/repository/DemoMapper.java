package com.lyun.estate.biz.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface DemoMapper {
    @Select("select now()")
    Date currentTime();
}

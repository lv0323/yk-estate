package com.lyun.estate.biz.clock.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface ClockMapper {
    @Select("select now()")
    Date currentTime();
}

package com.lyun.estate.biz.utils.clock.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface ClockMapper {

    @Select("SELECT NOW()")
    Date now();

}

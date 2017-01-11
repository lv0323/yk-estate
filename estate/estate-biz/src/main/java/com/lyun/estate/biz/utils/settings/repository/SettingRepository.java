package com.lyun.estate.biz.utils.settings.repository;

import com.lyun.estate.biz.utils.settings.def.NameSpace;
import com.lyun.estate.biz.utils.settings.entity.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SettingRepository {

    @Select("SELECT * FROM t_setting WHERE name_space = #{nameSpace} AND  key = #{key};")
    Setting find(@Param("nameSpace") NameSpace nameSpace, @Param("key") String key);

    @Select("SELECT * FROM t_setting WHERE id = #{id}")
    Setting findOne(@Param("id") Long id);


}

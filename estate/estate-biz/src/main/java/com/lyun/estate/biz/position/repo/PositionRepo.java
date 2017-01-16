package com.lyun.estate.biz.position.repo;

import com.lyun.estate.biz.position.entity.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PositionRepo {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_position (company_id,name,note) values (#{companyId},#{name},#{note})")
    int insert(Position position);

    @Delete("delete from t_position where id = #{id}")
    int deleteById(Long id);

    @Update("update t_position set name=#{name},note=#{note},update_time=CURRENT_TIMESTAMP where id=#{id}")
    int update(Position position);

    @Select("select * from t_position where id=#{id}")
    Position selectById(Long id);

    @Select("select * from t_position where company_id=#{companyId}")
    List<Position> selectByCompanyId(Long companyId);
}

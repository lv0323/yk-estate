package com.lyun.estate.mgt.position.repo;

import com.lyun.estate.mgt.position.entity.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PositionRepo {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_position (company_id,name,note) values (#{companyId},#{name},#{note})")
    int insert(Position position);

    @Delete("delete from t_position where id = #{id}")
    int deleteById(Long id);

    @Update("update t_position set company_id=#{companyId},name=#{name},note=#{note} where id=#{id}")
    int update(Position position);

    @Select("select * from t_position where company_id=#{companyId}")
    List<Position> selectByCompanyId(Long companyId);
}

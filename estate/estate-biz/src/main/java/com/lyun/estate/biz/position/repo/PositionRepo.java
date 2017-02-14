package com.lyun.estate.biz.position.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.position.entity.Position;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface PositionRepo {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_position (company_id,name,type, note) values (#{companyId},#{name},#{type}, #{note})")
    int insert(Position position);

    @Update("update t_position set is_deleted = true where id = #{id}")
    int deleteById(Long id);

    @Update("update t_position set name=#{name},note=#{note}, type=#{type}, update_time=CURRENT_TIMESTAMP where id=#{id} and is_deleted = false")
    int update(Position position);

    @Select("select * from t_position where id=#{id} and is_deleted = false")
    Position selectById(Long id);

    @Select("select * from t_position where company_id=#{companyId} and is_deleted = false")
    PageList<Position> selectByCompanyId(Long companyId, PageBounds pageBounds);
}

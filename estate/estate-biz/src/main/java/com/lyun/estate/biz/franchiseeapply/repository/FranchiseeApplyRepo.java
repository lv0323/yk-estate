package com.lyun.estate.biz.franchiseeapply.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.franchiseeapply.entity.FranchiseeApply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FranchiseeApplyRepo {

    @Insert("INSERT INTO t_franchisee_apply (city, type, name, gender, mobile, qq, email, message) VALUES (#{city}, #{type}, #{name}, #{gender}, #{mobile}, #{qq}, #{email}, #{message})")
    @Options(useGeneratedKeys = true)
    int create(FranchiseeApply franchiseeApply);

    @Select("SELECT * FROM t_franchisee_apply  order by id")
    List<FranchiseeApply> list(PageBounds pageBounds);

    @Select("SELECT * FROM t_franchisee_apply WHERE id = #{id}")
    FranchiseeApply findOne(Long id);
}

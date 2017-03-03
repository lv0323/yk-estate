package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.entity.FangContact;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangContactRepo {

    @Select("SELECT * FROM t_fang_contact where fang_id = #{fangId} ")
    List<FangContact> findByFangId(Long fangId);
}

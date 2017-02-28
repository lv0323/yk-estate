package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangInfoOwnerRepo {

    @Select("SELECT * FROM t_fang_info_owner where fang_id = #{fangId} and is_deleted = false")
    List<FangInfoOwner> findByFangId(Long fangId);
}

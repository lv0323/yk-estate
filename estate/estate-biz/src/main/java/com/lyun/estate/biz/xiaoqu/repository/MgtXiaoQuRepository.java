package com.lyun.estate.biz.xiaoqu.repository;

import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Repository
public interface MgtXiaoQuRepository {
    @Select("select * from t_xiao_qu where id =#{id}")
    XiaoQu findOne(Long id);
}

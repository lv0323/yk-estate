package com.lyun.estate.biz.fangcollect.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fangcollect.domain.FangPoolSelector;
import com.lyun.estate.biz.fangcollect.entity.FangPool;
import com.lyun.estate.biz.fangcollect.repo.provider.FangPoolSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Created by robin on 17/5/5.
 */
public interface FangPoolRepo {

    @InsertProvider(type = FangPoolSqlProvider.class, method = "createFangPool")
    @Options(useGeneratedKeys = true)
    int createFangPool(FangPool fangPool);

    @SelectProvider(type = FangPoolSqlProvider.class, method = "listSummary")
    PageList<FangPool> listSummary(FangPoolSelector selector, PageBounds pageBounds);

    @Select("SELECT * FROM t_fang_pool where id = #{fangPoolId} LIMIT 1")
    FangPool detail(Long fangPoolId);
}

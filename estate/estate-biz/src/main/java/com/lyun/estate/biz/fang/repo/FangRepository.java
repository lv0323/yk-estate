package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.entity.FangDescr;
import com.lyun.estate.biz.fang.entity.FangSelector;
import com.lyun.estate.biz.fang.entity.FangTag;
import com.lyun.estate.biz.fang.repo.provider.FangSqlProvider;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public interface FangRepository {

    @SelectProvider(type = FangSqlProvider.class, method = "findSummaryById")
    FangSummary findSummary(Long fangId);

    @Select("SELECT * FROM t_fang_tag WHERE fang_id = #{fangId}")
    List<FangTag> findTags(@Param("fangId") Long fangId);

    @Select("SELECT * FROM t_fang_descr WHERE fang_id = #{fangId}")
    FangDescr findDescr(@Param("fangId") Long fangId);

    @SelectProvider(type = FangSqlProvider.class, method = "findDetailById")
    FangDetail findDetail(Long fangId);

    @SelectProvider(type = FangSqlProvider.class, method = "findSummaryBySelector")
    PageList<FangSummary> findSummaryBySelector(FangSelector selector, PageBounds pageBounds);
}

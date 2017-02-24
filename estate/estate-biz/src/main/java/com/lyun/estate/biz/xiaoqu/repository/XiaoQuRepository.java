package com.lyun.estate.biz.xiaoqu.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuStationRel;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuDetailBean;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSelector;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSummaryBean;
import com.lyun.estate.biz.xiaoqu.repository.provider.XiaoQuSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Repository
public interface XiaoQuRepository {

    @SelectProvider(type = XiaoQuSqlProvider.class, method = "findSummaryBySelector")
    PageList<XiaoQuSummaryBean> findSummaryBySelector(XiaoQuSelector selector, PageBounds pageBounds);

    @SelectProvider(type = XiaoQuSqlProvider.class, method = "findDetail")
    XiaoQuDetailBean findDetail(@Param("id") Long id);



    @Select("SELECT xq.id as xiao_qu_id, csr.*, s.name as station_name" +
            "  FROM t_xiao_qu xq" +
            "  LEFT JOIN t_community c ON xq.community_id = c.id" +
            "  LEFT JOIN t_community_station_rel csr on csr.community_id = c.id" +
            "  LEFT JOIN t_station s on s.id = csr.station_id" +
            "  WHERE xq.id = #{xiaoQuId}")
    List<XiaoQuStationRel> findStations(@Param("xiaoQuId") Long xiaoQuId);

    @Select("SELECT  xq.id,  xq.ranking,  xq.avg_price, c.city_id, c.name,  dr.district_id,  c.sub_district_id,  c.builded_year,  c.structure_type" +
            " FROM t_xiao_qu xq LEFT JOIN t_community c ON xq.community_id = c.id LEFT JOIN t_district_rel dr on dr.sub_district_id = c.sub_district_id AND is_primary = 'Y'" +
            " WHERE xq.id = #{id}")
    XiaoQuSummaryBean findSummary(Long id);

}

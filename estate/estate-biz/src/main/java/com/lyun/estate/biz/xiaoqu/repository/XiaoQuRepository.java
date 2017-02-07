package com.lyun.estate.biz.xiaoqu.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.xiaoqu.entity.EstateMapResource;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuStationRel;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuDetailBean;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSelector;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSummaryBean;
import com.lyun.estate.biz.xiaoqu.repository.provider.XiaoQuSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Repository
public interface XiaoQuRepository {

    @SelectProvider(type = XiaoQuSqlProvider.class, method = "findSummary")
    PageList<XiaoQuSummaryBean> findSummary(XiaoQuSelector selector, PageBounds pageBounds);

    @SelectProvider(type = XiaoQuSqlProvider.class, method = "findDetail")
    XiaoQuDetailBean findDetail(@Param("id") Long id);

    @SelectProvider(type = XiaoQuSqlProvider.class, method = "findSellCommunityListByMap")
    List<EstateMapResource> findSellCommunityListByMap(@Param("minLongitude") BigDecimal minLongitude,
                                                       @Param("maxLongitude") BigDecimal maxLongitude,
                                                       @Param("minLatitude") BigDecimal minLatitude,
                                                       @Param("maxLatitude") BigDecimal maxLatitude,
                                                       @Param("cityId") Integer cityId);

    @SelectProvider(type = XiaoQuSqlProvider.class, method = "findRentCommunityListByMap")
    List<EstateMapResource> findRentCommunityListByMap(@Param("minLongitude") BigDecimal minLongitude,
                                                       @Param("maxLongitude") BigDecimal maxLongitude,
                                                       @Param("minLatitude") BigDecimal minLatitude,
                                                       @Param("maxLatitude") BigDecimal maxLatitude,
                                                       @Param("cityId") Integer cityId);

    @Select("SELECT xq.id as xiao_qu_id, csr.*, s.name as station_name" +
            "  FROM t_xiao_qu xq" +
            "  LEFT JOIN t_community c ON xq.community_id = c.id" +
            "  LEFT JOIN t_community_station_rel csr on csr.community_id = c.id" +
            "  LEFT JOIN t_station s on s.id = csr.station_id" +
            "  WHERE xq.id = #{xiaoQuId}")
    List<XiaoQuStationRel> findStations(@Param("xiaoQuId") Long xiaoQuId);
}

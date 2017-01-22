package com.lyun.estate.biz.spec.xiaoqu.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.fang.def.BizType;
import com.lyun.estate.biz.spec.xiaoqu.def.XQSummaryOrder;
import com.lyun.estate.biz.spec.xiaoqu.entity.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public interface XiaoQuService {

    PageList<XiaoQuSummary> findXiaoQuSummaryByKeyword(XiaoQuFilter filter, XQSummaryOrder order,
                                                       PageBounds pageBounds);

    List<KeywordResp> keywords(Long cityId, String keyword);

    XiaoQuDetail getDetail(Long id);

    PageList<XiaoQuSummary> findNearbyXiaoQu(Long id);

    /**
     * 查找城市所有区域信息
     *
     * @return
     */
    List<EstateMapResource> findAllDistrictListByMap(int cityId, BizType bizType);

    /**
     * 查找板块所有信息
     *
     * @param cityId
     * @return
     */
    List<EstateMapResource> findAllSubDistrictListByMap(int cityId, BizType bizType);

    /**
     * 查找坐标内所有小区信息
     *
     * @param minLongitude
     * @param maxLongitude
     * @param minLatitude
     * @param maxLatitude
     * @return
     */
    List<EstateMapResource> findCommunityListByMap(BigDecimal minLongitude, BigDecimal maxLongitude,
                                                   BigDecimal minLatitude, BigDecimal maxLatitude, BizType bizType,Integer cityId);
}

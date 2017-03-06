package com.lyun.estate.biz.spec.fang.rest.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.fang.rest.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.rest.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummaryOrder;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public interface FangService {

    PageList<FangSummary> findFangSummaryByKeyword(FangFilter filter, FangSummaryOrder order,
                                                   PageBounds pageBounds);

    FangDetail getDetail(Long id);

    /* 根据房屋ID查询房源信息 */
    FangSummary getSummary(Long id);

    PageList<FangSummary> findSummaryByXiaoQuId(Long cityId, Long xiaoQuId, BizType bizType, PageBounds pageBounds);

    PageList<FangSummary> findNearbyByFangId(Long id);

    int updateKeyword(Long fangId, String keyword);

    List<FileDescription> files(Long ownerId, CustomType customType);
}

package com.lyun.estate.biz.spec.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.entity.FangSummaryOrder;

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

    List<Fang> findAllFang();

    List<FileDescription> files(Long ownerId, CustomType customType);
}

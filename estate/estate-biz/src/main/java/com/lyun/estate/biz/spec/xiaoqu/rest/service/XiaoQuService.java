package com.lyun.estate.biz.spec.xiaoqu.rest.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.xiaoqu.rest.def.XQSummaryOrder;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuStationRel;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuSummary;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public interface XiaoQuService {

    PageList<XiaoQuSummary> findXiaoQuSummaryByKeyword(XiaoQuFilter filter, XQSummaryOrder order,
                                                       PageBounds pageBounds);

    XiaoQuSummary getSummary(Long id);

    XiaoQuDetail getDetail(Long id);

    PageList<XiaoQuSummary> findNearbyXiaoQu(Long id);

    List<XiaoQuStationRel> findStations(Long xiaoQuId);

    List<FileDescription> files(Long xiaoQuId, CustomType customType);
}

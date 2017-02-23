package com.lyun.estate.biz.spec.xiaoqu.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.xiaoqu.def.XQSummaryOrder;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuStationRel;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuSummary;
import com.lyun.estate.biz.xiaoqu.entity.Community;

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

    int updateKeyword(Long id, String keyword);

    List<Community> findAllCommunity();

    List<FileDescription> files(Long xiaoQuId, CustomType customType);
}

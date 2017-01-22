package com.lyun.estate.biz.spec.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.xiaoqu.def.XQSummaryOrder;
import com.lyun.estate.biz.spec.xiaoqu.entity.KeywordResp;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public interface FangService {

    List<KeywordResp> keywords(Long cityId, String keyword);

    PageList<FangSummary> findFangSummaryByKeyword(FangFilter filter, XQSummaryOrder order,
                                                   PageBounds pageBounds);

    FangDetail getDetail(Long id);

    /* 根据房屋ID查询房源信息 */
    FangSummary getSummary(Long id);

}

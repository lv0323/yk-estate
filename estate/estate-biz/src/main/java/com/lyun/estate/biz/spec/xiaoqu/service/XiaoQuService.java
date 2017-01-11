package com.lyun.estate.biz.spec.xiaoqu.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuSummary;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public interface XiaoQuService {

    PageList<XiaoQuSummary> findXiaoQuSummaryByKeyword(XiaoQuFilter filter, PageBounds pageBounds);
}

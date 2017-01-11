package com.lyun.estate.biz.xiaoqu.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSelector;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSummaryBean;
import com.lyun.estate.biz.xiaoqu.repository.provider.XiaoQuSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Repository
public interface XiaoQuRepository {

    @SelectProvider(type = XiaoQuSqlProvider.class, method = "findSummary")
    PageList<XiaoQuSummaryBean> findSummary(XiaoQuSelector selector, PageBounds pageBounds);
}

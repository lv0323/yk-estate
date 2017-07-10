package com.lyun.estate.biz.spec.xiaoqu.mgt.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuSummary;
import com.lyun.estate.biz.xiaoqu.entity.CommunityEntity;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuEntity;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public interface MgtXiaoQuService {

    XiaoQu findOne(Long id);

    PageList<MgtXiaoQuSummary> list(MgtXiaoQuFilter filter, PageBounds pageBounds);

    MgtXiaoQuDetail detail(Long id);

    XiaoQuEntity createXiaoQu(String name, String alias, long cityId, long subDistrictId);

    int updateXiaoQu(CommunityEntity communityEntity);
}

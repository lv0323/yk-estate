package com.lyun.estate.mgt.fangCollect.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fangcollect.domain.FangPoolSelector;
import com.lyun.estate.biz.fangcollect.entity.FangPool;
import com.lyun.estate.biz.fangcollect.entity.FangPoolOrder;
import com.lyun.estate.biz.fangcollect.repo.FangPoolRepo;
import com.lyun.estate.biz.fangcollect.service.MgtFangCollectService;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by robin on 17/5/5.
 */

@Service
public class FangCollectService {

    @Autowired
    MgtFangCollectService mgtFangCollectService;

    public PageList<FangPool> listSummary(FangPoolSelector selector, FangPoolOrder order, PageBounds pageBounds) {
        PageList<FangPool> fangPools = mgtFangCollectService.listSummary(selector, order, pageBounds);
        return fangPools;
    }

    public FangPool detail(Long fangPoolId){
        return mgtFangCollectService.detail(fangPoolId);
    }
}


package com.lyun.estate.biz.fangcollect.service;

/**
 * Created by robin on 17/5/5.
 */

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fangcollect.domain.FangPoolSelector;
import com.lyun.estate.biz.fangcollect.entity.FangPool;
import com.lyun.estate.biz.fangcollect.entity.FangPoolDistrict;
import com.lyun.estate.biz.fangcollect.entity.FangPoolOrder;
import com.lyun.estate.biz.fangcollect.repo.FY01Repo;
import com.lyun.estate.biz.fangcollect.repo.FangPoolRepo;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MgtFangCollectService {

    @Autowired
    private FY01Repo fy01Repo;

    @Autowired
    private FangPoolRepo fangPoolRepo;

    public PageList<FangPool> listSummary(FangPoolSelector selector, FangPoolOrder order, PageBounds pageBounds) {

        pageBounds.getOrders().clear();
        pageBounds.getOrders().addAll(order.getOrders());
        PageList<FangPool> fangPools = fangPoolRepo.listSummary(selector,pageBounds);
        return fangPools;
    }

    public FangPoolDistrict getDistrictByName(String name) {
        return fy01Repo.getDistrictByName(name).stream().findFirst().orElseThrow(() -> new EstateException(ExCode.NOT_FOUND,"district:name", name));
    }

    public int createFangPool(FangPool fangPool) {
        return fangPoolRepo.createFangPool(fangPool);
    }

    public FangPool detail(Long fangPoolId){
        return fangPoolRepo.detail(fangPoolId);
    }

}

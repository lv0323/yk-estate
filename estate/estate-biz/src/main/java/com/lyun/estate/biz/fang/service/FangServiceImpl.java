package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.housedict.service.HouseService;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.entity.FangSummaryOrder;
import com.lyun.estate.biz.spec.fang.service.FangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-01-20.
 */
@Service
public class FangServiceImpl implements FangService {


    @Autowired
    private HouseService houseService;


    @Override
    public PageList<FangSummary> findFangSummaryByKeyword(FangFilter filter, FangSummaryOrder order,
                                                          PageBounds pageBounds) {


        return null;
    }

    @Override
    public FangDetail getDetail(Long id) {
        return null;
    }

    @Override
    public FangSummary getSummary(Long id) {
        //TODO 实现
        return null;
    }

}

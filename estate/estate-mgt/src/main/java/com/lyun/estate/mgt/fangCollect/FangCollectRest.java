package com.lyun.estate.mgt.fangCollect;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fangcollect.domain.FangPoolSelector;
import com.lyun.estate.biz.fangcollect.entity.FangPool;
import com.lyun.estate.biz.fangcollect.entity.FangPoolOrder;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.mgt.fangCollect.service.FangCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/fangCollect")
public class FangCollectRest {

    @Autowired
    private FangCollectService fangCollectService;

    @GetMapping("list")
    public PageList<FangPool> listSummary(@RequestParam Long cityId,
                                          @RequestParam(required = false) BizType bizType,
                                          @RequestParam(required = false) Long districtId,
                                          @RequestParam(required = false) HouseType houseType,
                                          @RequestParam(required = false) Integer sCounts,
                                          @RequestParam FangPoolOrder order,
                                          @RequestHeader("X-PAGING") PageBounds pageBounds) {
        FangPoolSelector selector = new FangPoolSelector().setBizType(bizType)
        .setCityId(cityId)
                .setDistrictId(districtId)
                .setHouseType(houseType)
                .setsCounts(sCounts);
        return fangCollectService.listSummary(selector, order, pageBounds);
    }

    @GetMapping("detail")
    public FangPool detail(@RequestParam Long fangPoolId) {
        return fangCollectService.detail(fangPoolId);
    }
}

package com.lyun.estate.rest.fang;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.keyword.entity.KeywordResp;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.entity.FangSummaryOrder;
import com.lyun.estate.biz.spec.fang.service.FangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-02-03.
 */
@RequestMapping("fang")
public class FangController {

    @Autowired
    private FangService fangService;

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/keywords")
    public List<KeywordResp> keywords(@RequestParam Long cityId,
                                      @RequestParam String keyword) {
        return keywordService.decorate(keywordService.findContain(keyword, cityId,
                Lists.newArrayList(DomainType.DISTRICT,
                        DomainType.SUB_DISTRICT,
                        DomainType.LINE,
                        DomainType.STATION,
                        DomainType.XIAO_QU), 10));
    }

    @GetMapping("/")
    public PageList<FangSummary> summary(@RequestParam Long cityId,
                                         @RequestParam BizType bizType,
                                         @RequestParam(required = false) FangSummaryOrder order,
                                         @RequestHeader("X-PAGING") PageBounds pageBounds) {

        FangFilter fangFilter = new FangFilter();
        fangFilter.setCityId(cityId);
        fangFilter.setBizType(bizType);

        return fangService.findFangSummaryByKeyword(fangFilter,
                Optional.ofNullable(order).orElse(FangSummaryOrder.DEFAULT),
                pageBounds);
    }

    @GetMapping("/{id}")
    public FangDetail detail(@PathVariable Long id) {
        return fangService.getDetail(id);
    }

}

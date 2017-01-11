package com.lyun.estate.rest.xiaoqu;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.houselicence.def.StructureType;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jeffrey on 2017-01-10.
 */
@RestController
@RequestMapping("/xiaoqu")
public class XiaoQuController {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private XiaoQuService xiaoQuService;

    @GetMapping(value = "/summary")
    public PageList<XiaoQuSummary> summary(@RequestParam(required = false) String district,
                                           @RequestParam(required = false) String subDistrict,
                                           @RequestParam(required = false) StructureType structureType,
                                           @RequestParam(required = false) Integer minPrice,
                                           @RequestParam(required = false) Integer maxPrice,
                                           @RequestParam(required = false) Integer minYear,
                                           @RequestParam(required = false) Integer maxYear,
                                           @RequestParam(required = false) String keyword,
                                           @RequestHeader("X-PAGING") PageBounds pageBounds) {
        XiaoQuFilter filter = new XiaoQuFilter().setDistrict(district)
                .setSubDistrict(subDistrict)
                .setStructureType(structureType)
                .setMinYear(minYear)
                .setMaxYear(maxYear)
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .setKeyword(keyword);
        return xiaoQuService.findXiaoQuSummaryByKeyword(filter, pageBounds);
    }

}

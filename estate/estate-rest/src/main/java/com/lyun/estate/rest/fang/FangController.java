package com.lyun.estate.rest.fang;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.keyword.entity.KeywordResp;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.fang.def.ElevatorFilter;
import com.lyun.estate.biz.spec.fang.def.ShiCountsFilter;
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
@RestController
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
                                         @RequestParam(required = false) Long districtId,
                                         @RequestParam(required = false) Long subDistrictId,
                                         @RequestParam(required = false) Long lineId,
                                         @RequestParam(required = false) Long stationId,
                                         @RequestParam(required = false) List<ShiCountsFilter> scfs,
                                         @RequestParam(required = false) List<Orientation> os,
                                         @RequestParam(required = false) List<HouseTag> hts,
                                         @RequestParam(required = false) List<FloorType> fts,
                                         @RequestParam(required = false) List<Decorate> ds,
                                         @RequestParam(required = false) List<ElevatorFilter> efs,
                                         @RequestParam(required = false) List<StructureType> sts,
                                         @RequestParam(required = false) Integer minPrice,
                                         @RequestParam(required = false) Integer maxPrice,
                                         @RequestParam(required = false) Integer minYear,
                                         @RequestParam(required = false) Integer maxYear,
                                         @RequestParam(required = false) Integer minArea,
                                         @RequestParam(required = false) Integer maxArea,
                                         @RequestParam(required = false) String keyword,
                                         @RequestParam(required = false) FangSummaryOrder order,
                                         @RequestHeader("X-PAGING") PageBounds pageBounds) {

        FangFilter fangFilter = new FangFilter();
        fangFilter.setCityId(cityId)
                .setBizType(bizType)
                .setDistrictId(districtId)
                .setSubDistrictId(subDistrictId)
                .setLineId(lineId)
                .setStationId(stationId)
                .setShiCountsFilters(scfs)
                .setOrientations(os)
                .setHouseTags(hts)
                .setFloorTypes(fts)
                .setDecorates(ds)
                .setElevatorFilters(efs)
                .setStructureTypes(sts)
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .setMinYear(minYear)
                .setMaxYear(maxYear)
                .setMinArea(minArea)
                .setMaxArea(maxArea)
                .setKeyword(keyword);

        return fangService.findFangSummaryByKeyword(fangFilter,
                Optional.ofNullable(order).orElse(FangSummaryOrder.DEFAULT),
                pageBounds);
    }

    @GetMapping("/{id}")
    public FangDetail detail(@PathVariable Long id) {
        return fangService.getDetail(id);
    }

}

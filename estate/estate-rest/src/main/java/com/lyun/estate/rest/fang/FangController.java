package com.lyun.estate.rest.fang;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.keyword.entity.KeywordResp;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.fang.rest.def.ElevatorFilter;
import com.lyun.estate.biz.spec.fang.rest.def.HouseTypeFilter;
import com.lyun.estate.biz.spec.fang.rest.def.IntPair;
import com.lyun.estate.biz.spec.fang.rest.def.ShiCountsFilter;
import com.lyun.estate.biz.spec.fang.rest.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.rest.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummaryOrder;
import com.lyun.estate.biz.spec.fang.rest.service.FangService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.support.def.DomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
                                      @RequestParam String keyword,
                                      @RequestParam BizType bizType) {
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
                                         @RequestParam(required = false) List<String> yips,
                                         @RequestParam(required = false) List<String> aips,
                                         @RequestParam(required = false) HouseProcess process,
                                         @RequestParam(required = false) String keyword,
                                         @RequestParam(required = false) List<HouseTypeFilter> htfs,
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
                .setProcess(process)
                .setKeyword(keyword)
                .setHouseTypeFilters(htfs)
                .setYears(Optional.ofNullable(yips).map(t -> t.stream()
                        .map(IntPair::fromIntPairStr)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())).orElse(null))
                .setAreas(Optional.ofNullable(aips).map(t -> t.stream()
                        .map(IntPair::fromIntPairStr)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())).orElse(null));

        return fangService.findFangSummaryByKeyword(fangFilter,
                Optional.ofNullable(order).orElse(FangSummaryOrder.DEFAULT),
                pageBounds);
    }

    @GetMapping("/{id}")
    public FangDetail detail(@PathVariable Long id) {
        return fangService.getDetail(id);
    }

    @GetMapping("/xiaoqu")
    public PageList<FangSummary> summary(@RequestParam Long xiaoQuId,
                                         @RequestParam BizType bizType,
                                         @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return fangService.findSummaryByXiaoQuId(xiaoQuId, bizType, pageBounds);
    }

    @GetMapping("/nearby")
    public PageList<FangSummary> nearby(@RequestParam Long fangId) {
        return fangService.findNearbyByFangId(fangId);
    }

    @GetMapping("/files")
    public List<FileDescription> file(@RequestParam Long ownerId,
                                      @RequestParam(required = false) CustomType customType) {

        return fangService.files(ownerId, customType);
    }

    @GetMapping("/recommend")
    public PageList<FangSummary> recommend(@RequestParam Long cityId,
                                           @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return fangService.recommendSellFang(cityId, pageBounds);
    }
}

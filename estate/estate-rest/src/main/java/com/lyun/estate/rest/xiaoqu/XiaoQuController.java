package com.lyun.estate.rest.xiaoqu;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.fang.def.StructureType;
import com.lyun.estate.biz.keyword.entity.KeywordResp;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.spec.xiaoqu.rest.def.XQSummaryOrder;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-01-10.
 */
@RestController
@RequestMapping("/xiaoqus")
public class XiaoQuController {

    @Autowired
    private XiaoQuService xiaoQuService;

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/keywords")
    public List<KeywordResp> keywords(@RequestParam Long cityId,
                                      @RequestParam String keyword) {
        return keywordService.decorate(keywordService.findContain(keyword, cityId,
                Lists.newArrayList(DomainType.DISTRICT, DomainType.SUB_DISTRICT, DomainType.XIAO_QU), 10));
    }

    @GetMapping("/")
    public PageList<XiaoQuSummary> summary(@RequestParam Long cityId,
                                           @RequestParam(required = false) Long districtId,
                                           @RequestParam(required = false) Long subDistrictId,
                                           @RequestParam(required = false) Long lineId,
                                           @RequestParam(required = false) Long stationId,
                                           @RequestParam(required = false) StructureType structureType,
                                           @RequestParam(required = false) Integer minPrice,
                                           @RequestParam(required = false) Integer maxPrice,
                                           @RequestParam(required = false) Integer minYear,
                                           @RequestParam(required = false) Integer maxYear,
                                           @RequestParam(required = false) String keyword,
                                           @RequestParam(required = false) XQSummaryOrder order,
                                           @RequestHeader("X-PAGING") PageBounds pageBounds) {
        XiaoQuFilter filter = new XiaoQuFilter()
                .setCityId(cityId)
                .setDistrictId(districtId)
                .setSubDistrictId(subDistrictId)
                .setLineId(lineId)
                .setStationId(stationId)
                .setStructureType(structureType)
                .setMinYear(minYear)
                .setMaxYear(maxYear)
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .setKeyword(keyword);

        return xiaoQuService.findXiaoQuSummaryByKeyword(filter,
                Optional.ofNullable(order).orElse(XQSummaryOrder.DEFAULT),
                pageBounds);
    }

    @GetMapping(value = "/{id}")
    public XiaoQuDetail detail(@PathVariable Long id) {
        return xiaoQuService.getDetail(id);
    }

    @GetMapping(value = "/nearby")
    public PageList<XiaoQuSummary> nearby(@RequestParam Long id) {
        return xiaoQuService.findNearbyXiaoQu(id);
    }

    @GetMapping("/updateAllKeyword")
    public boolean updateAllKeyword() {
        return keywordService.updateAllKeyword();
    }

    @GetMapping("/files")
    public List<FileDescription> file(@RequestParam Long ownerId,
                                      @RequestParam(required = false) CustomType customType) {
        return xiaoQuService.files(ownerId, customType);
    }
}

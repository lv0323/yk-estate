package com.lyun.estate.rest.map;

import com.lyun.estate.biz.spec.fang.def.BizType;
import com.lyun.estate.biz.spec.xiaoqu.entity.EstateMapResource;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jesse on 2017/1/19.
 */
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private XiaoQuService xiaoQuService;

    @GetMapping("/sell/district")
    List<EstateMapResource> sellDistrict(@RequestParam Integer cityId) {
        return xiaoQuService.findAllDistrictListByMap(cityId, BizType.SELL);
    }

    @GetMapping("/rent/district")
    List<EstateMapResource> rentDistrict(@RequestParam Integer cityId) {
        return xiaoQuService.findAllDistrictListByMap(cityId, BizType.RENT);
    }

    @GetMapping("/sell/subDistrict")
    List<EstateMapResource> sellSubDistrict(@RequestParam Integer cityId) {
        return xiaoQuService.findAllSubDistrictListByMap(cityId, BizType.SELL);
    }

    @GetMapping("/rent/subDistrict")
    List<EstateMapResource> rentSubDistrict(@RequestParam Integer cityId) {
        return xiaoQuService.findAllSubDistrictListByMap(cityId, BizType.RENT);
    }

    @GetMapping("/sell/community")
    List<EstateMapResource> sellCommunity(@RequestParam(required = true) BigDecimal minLongitude,
                                          @RequestParam(required = true) BigDecimal maxLongitude,
                                          @RequestParam(required = true) BigDecimal minLatitude,
                                          @RequestParam(required = true) BigDecimal maxLatitude) {
        return xiaoQuService.findCommunityListByMap(minLongitude, maxLongitude, minLatitude, maxLatitude, BizType.SELL);
    }

    @GetMapping("/rent/community")
    List<EstateMapResource> rentCommunity(@RequestParam(required = true) BigDecimal minLongitude,
                                          @RequestParam(required = true) BigDecimal maxLongitude,
                                          @RequestParam(required = true) BigDecimal minLatitude,
                                          @RequestParam(required = true) BigDecimal maxLatitude) {
        return xiaoQuService.findCommunityListByMap(minLongitude, maxLongitude, minLatitude, maxLatitude, BizType.RENT);
    }
}

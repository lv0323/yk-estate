package com.lyun.estate.rest.map;

import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.map.service.MapService;
import com.lyun.estate.biz.map.domain.EstateMapResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jesse on 2017/1/19.
 */
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/sell/district")
    List<EstateMapResource> sellDistrict(@RequestParam Integer cityId, HttpServletResponse response) {
        response.addHeader("Cache-Control", "max-age=600");
        return mapService.findAllDistrictListByMap(cityId, BizType.SELL);
    }

    @GetMapping("/rent/district")
    List<EstateMapResource> rentDistrict(@RequestParam Integer cityId, HttpServletResponse response) {
        response.addHeader("Cache-Control", "max-age=600");
        return mapService.findAllDistrictListByMap(cityId, BizType.RENT);
    }

    @GetMapping("/sell/subDistrict")
    List<EstateMapResource> sellSubDistrict(@RequestParam Integer cityId, HttpServletResponse response) {
        response.addHeader("Cache-Control", "max-age=600");
        return mapService.findAllSubDistrictListByMap(cityId, BizType.SELL);
    }

    @GetMapping("/rent/subDistrict")
    List<EstateMapResource> rentSubDistrict(@RequestParam Integer cityId, HttpServletResponse response) {
        response.addHeader("Cache-Control", "max-age=600");
        return mapService.findAllSubDistrictListByMap(cityId, BizType.RENT);
    }

    @GetMapping("/sell/xiaoqu")
    List<EstateMapResource> sellCommunity(@RequestParam(required = true) BigDecimal minLongitude,
                                          @RequestParam(required = true) BigDecimal maxLongitude,
                                          @RequestParam(required = true) BigDecimal minLatitude,
                                          @RequestParam(required = true) BigDecimal maxLatitude,
                                          @RequestParam Integer cityId,
                                          HttpServletResponse response) {
        response.addHeader("Cache-Control", "max-age=600");
        return mapService.findCommunityListByMap(minLongitude, maxLongitude, minLatitude, maxLatitude, BizType.SELL, cityId);
    }

    @GetMapping("/rent/xiaoqu")
    List<EstateMapResource> rentCommunity(@RequestParam(required = true) BigDecimal minLongitude,
                                          @RequestParam(required = true) BigDecimal maxLongitude,
                                          @RequestParam(required = true) BigDecimal minLatitude,
                                          @RequestParam(required = true) BigDecimal maxLatitude,
                                          @RequestParam Integer cityId,
                                          HttpServletResponse response) {
        response.addHeader("Cache-Control", "max-age=600");
        return mapService.findCommunityListByMap(minLongitude, maxLongitude, minLatitude, maxLatitude, BizType.RENT, cityId);
    }
}

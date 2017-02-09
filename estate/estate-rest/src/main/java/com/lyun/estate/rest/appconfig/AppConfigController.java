package com.lyun.estate.rest.appconfig;

import com.lyun.estate.biz.appconfig.entity.RegionConfig;
import com.lyun.estate.biz.appconfig.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jeffrey on 2017-01-16.
 */
@RestController
@RequestMapping("configs")
public class AppConfigController {

    @Autowired
    private AppConfigService appConfigService;

    @GetMapping("cities")
    public RegionConfig getCities() {
        return appConfigService.findCities();
    }

    @GetMapping("districts")
    public RegionConfig getDistricts(@RequestParam Long cityId) {
        return appConfigService.findDistrictRel(cityId);
    }

    @GetMapping("lines")
    public RegionConfig getStations(@RequestParam Long cityId) {
        return appConfigService.findLineRel(cityId);
    }

}

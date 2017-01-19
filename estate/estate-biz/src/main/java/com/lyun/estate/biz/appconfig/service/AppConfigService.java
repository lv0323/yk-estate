package com.lyun.estate.biz.appconfig.service;

import com.lyun.estate.biz.appconfig.entity.Region;
import com.lyun.estate.biz.appconfig.entity.RegionConfig;
import com.lyun.estate.biz.appconfig.repository.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-16.
 */
@Service
public class AppConfigService {

    @Autowired
    private AppConfigRepository repository;

    public RegionConfig findCities() {
        List<Region> regions = repository.findCities();
        Date lastUpdatedTime = repository.findCityLastUpdatedTime();
        return new RegionConfig().setRegions(regions).setLastUpdatedTime(lastUpdatedTime);
    }

    public RegionConfig findDistricts(Long cityId) {
        List<Region> regions = repository.findDistricts(cityId);
        regions.forEach(region -> region.setSubs(repository.findSubDistricts(region.getId())));
        Date lastUpdatedTime = repository.findDistrictsRelLastUpdatedTime(cityId);

        return new RegionConfig().setRegions(regions).setLastUpdatedTime(lastUpdatedTime);
    }
}

package com.lyun.estate.biz.appconfig.service;

import com.lyun.estate.biz.appconfig.entity.AndroidVersion;
import com.lyun.estate.biz.appconfig.entity.Region;
import com.lyun.estate.biz.appconfig.entity.RegionConfig;
import com.lyun.estate.biz.appconfig.entity.ServicePhone;
import com.lyun.estate.biz.appconfig.repository.AppConfigRepository;
import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.Line;
import com.lyun.estate.biz.housedict.service.CityService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.support.settings.SettingProvider;
import com.lyun.estate.biz.support.settings.def.NameSpace;
import com.lyun.estate.biz.support.settings.entity.Setting;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.lyun.estate.biz.support.settings.def.NameSpace.ANDROID_VERSION;

/**
 * Created by Jeffrey on 2017-01-16.
 */
@Service
public class AppConfigService {

    @Autowired
    private AppConfigRepository repository;

    @Autowired
    private CityService cityService;

    @Autowired
    private SettingProvider settingProvider;

    public RegionConfig findCities() {
        List<City> cities = cityService.findCities();
        List<Region> regions = cities.stream().map(city -> {
            Region region = new Region();
            BeanUtils.copyProperties(city, region);
            region.setRegionBound(city.getViewRegion());
            region.setType(DomainType.CITY);
            return region;
        }).collect(Collectors.toList());
        Date lastUpdatedTime = repository.findCityLastUpdatedTime();
        return new RegionConfig().setRegions(regions).setLastUpdatedTime(lastUpdatedTime);
    }

    public RegionConfig findDistrictRel(Long cityId) {
        List<District> districts = cityService.findOrderedDistricts(cityId);
        List<Region> regions = districts.stream().map(district -> {
            Region region = new Region();
            BeanUtils.copyProperties(district, region);
            region.setType(DomainType.DISTRICT);
            region.setRegionBound(district.getViewRegion());
            region.setSubs(cityService.findOrderedSubDistricts(district.getId()).stream().map(
                    subDistrict -> {
                        Region r = new Region();
                        BeanUtils.copyProperties(subDistrict, r);
                        r.setType(DomainType.SUB_DISTRICT);
                        r.setRegionBound(subDistrict.getViewRegion());
                        return r;
                    }
            ).collect(Collectors.toList()));
            return region;
        }).collect(Collectors.toList());

        Date lastUpdatedTime = repository.findDistrictRelLastUpdatedTime(cityId);
        return new RegionConfig().setRegions(regions).setLastUpdatedTime(lastUpdatedTime);
    }

    public RegionConfig findLineRel(Long cityId) {
        List<Line> lines = cityService.findOrderedLines(cityId);
        List<Region> regions = lines.stream().map(line -> {
            Region region = new Region();
            BeanUtils.copyProperties(line, region);
            region.setType(DomainType.LINE);
            region.setSubs(cityService.findOrderedStations(line.getId()).stream().map(
                    station -> {
                        Region r = new Region();
                        BeanUtils.copyProperties(station, r);
                        r.setType(DomainType.STATION);
                        return r;
                    }
            ).collect(Collectors.toList()));
            return region;
        }).collect(Collectors.toList());

        Date lastUpdatedTime = repository.findLineRelLastUpdatedTime(cityId);
        return new RegionConfig().setRegions(regions).setLastUpdatedTime(lastUpdatedTime);
    }

    public AndroidVersion getAndroidLastVersion() {
        String version = settingProvider.find(ANDROID_VERSION, "VERSION").getValue();
        boolean forceUpdate = Boolean.parseBoolean(settingProvider.find(ANDROID_VERSION, "FORCE_UPDATE").getValue());
        String url = settingProvider.find(ANDROID_VERSION, "URL").getValue();
        return new AndroidVersion().setVersion(version)
                .setForceUpdate(forceUpdate)
                .setUrl(url);
    }

    public ServicePhone getServicePhone() {
        Setting phoneNo = settingProvider.find(NameSpace.CONFIG, "phoneNo");
        return new ServicePhone()
                .setPhoneNo(phoneNo.getValue());
    }
}

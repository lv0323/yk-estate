package com.lyun.estate.biz.housedict.service;

import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.housedict.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    public List<City> findCities() {
        return cityRepository.findCities();
    }

    public List<District> findOrderedDistrictsByCityId(Long cityId) {
        return cityRepository.findOrderedDistrictsByCityId(cityId);
    }

    public List<SubDistrict> findOrderedSubDistrictsByDistrictId(Long districtId) {
        return cityRepository.findOrderedSubDistrictsByDistrictId(districtId);
    }

    public District findPrimaryDistrict(Long subDistrictId) {
        return cityRepository.findPrimaryDistrict(subDistrictId);
    }

    public City findCity(Long id) {
        return cityRepository.findCity(id);
    }

    public District findDistrict(Long id) {
        return cityRepository.findDistrict(id);
    }

    public SubDistrict findSubDistrict(Long id) {
        return cityRepository.findSubDistrict(id);
    }
}

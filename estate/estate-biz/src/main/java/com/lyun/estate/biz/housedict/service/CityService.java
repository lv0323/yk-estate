package com.lyun.estate.biz.housedict.service;

import com.lyun.estate.biz.housedict.entity.*;
import com.lyun.estate.biz.housedict.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    public List<City> findCities() {
        return cityRepository.findCities();
    }

    public List<District> findOrderedDistricts(Long cityId) {
        return cityRepository.findOrderedDistricts(cityId);
    }

    public List<SubDistrict> findOrderedSubDistricts(Long districtId) {
        return cityRepository.findOrderedSubDistricts(districtId);
    }

    public List<Line> findOrderedLines(Long cityId) {
        return cityRepository.findOrderedLines(cityId);
    }

    public List<Station> findOrderedStations(Long lineId){
        return cityRepository.findOrderedStations(lineId);
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

    public Line findPrimaryLine(Long stationId) {
        return cityRepository.findPrimaryLine(stationId);
    }

    @Transactional
    public int updateCityKeyword(Long id, String keyword) {
        return cityRepository.updateCityKeyword(id, keyword);
    }

    @Transactional
    public int updateDistrictKeyword(Long id, String keyword) {
        return cityRepository.updateDistrictKeyword(id, keyword);
    }

    @Transactional
    public int updateSubDistrictKeyword(Long id, String keyword) {
        return cityRepository.updateSubDistrictKeyword(id, keyword);
    }

    @Transactional
    public int updateLineKeyword(Long id, String keyword) {
        return cityRepository.updateLineKeyword(id, keyword);
    }

    @Transactional
    public int updateStationKeyword(Long id, String keyword) {
        return cityRepository.updateStationKeyword(id, keyword);
    }
}

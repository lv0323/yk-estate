package com.lyun.estate.biz.housedict.service;

import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.housedict.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    private final DistrictRepository repository;

    public DistrictService(DistrictRepository repository) {
        this.repository = repository;
    }

    public List<City> findCities() {
        return repository.findCities();
    }

    public List<District> findDistrictsByCityId(Long id) {
        return repository.findDistrictsByCityId(id);
    }

    public List<SubDistrict> findSubDistrictsByDistrictId(Long id) {
        return repository.findSubDistrictsByDistrictId(id);
    }
}

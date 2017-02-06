package com.lyun.estate.biz.housedict.service;

import com.lyun.estate.biz.housedict.repository.HouseRepository;
import com.lyun.estate.biz.spec.xiaoqu.entity.EstateMapResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-10.
 */
@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public List<EstateMapResource> findAllSellDistrictListByMap(int cityId) {
        return houseRepository.findAllSellDistrictListByMap(cityId);
    }

    public List<EstateMapResource> findAllRentDistrictListByMap(int cityId) {
        return houseRepository.findAllRentDistrictListByMap(cityId);
    }

    public List<EstateMapResource> findAllSellSubDistrictListByMap(int cityId) {
        return houseRepository.findAllSellSubDistrictListByMap(cityId);
    }

    public List<EstateMapResource> findAllRentSubDistrictListByMap(int cityId) {
        return houseRepository.findAllRentSubDistrictListByMap(cityId);
    }
}

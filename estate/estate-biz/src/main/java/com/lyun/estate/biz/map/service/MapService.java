package com.lyun.estate.biz.map.service;

import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.map.repo.MapRepository;
import com.lyun.estate.biz.map.domain.EstateMapResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-10.
 */
@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    private List<EstateMapResource> findAllSellDistrictListByMap(int cityId) {
        return mapRepository.findAllSellDistrictListByMap(cityId);
    }

    private List<EstateMapResource> findAllRentDistrictListByMap(int cityId) {
        return mapRepository.findAllRentDistrictListByMap(cityId);
    }

    private List<EstateMapResource> findAllSellSubDistrictListByMap(int cityId) {
        return mapRepository.findAllSellSubDistrictListByMap(cityId);
    }

    private List<EstateMapResource> findAllRentSubDistrictListByMap(int cityId) {
        return mapRepository.findAllRentSubDistrictListByMap(cityId);
    }


    public List<EstateMapResource> findAllDistrictListByMap(int cityId, BizType bizType) {
        if (bizType == BizType.SELL) {
            return findAllSellDistrictListByMap(cityId);
        } else if (bizType == BizType.RENT) {
            return findAllRentDistrictListByMap(cityId);
        }
        return null;
    }

    public List<EstateMapResource> findAllSubDistrictListByMap(int cityId, BizType bizType) {
        if (bizType == BizType.SELL) {
            return findAllSellSubDistrictListByMap(cityId);
        } else if (bizType == BizType.RENT) {
            return findAllRentSubDistrictListByMap(cityId);
        }
        return null;
    }

    public List<EstateMapResource> findCommunityListByMap(BigDecimal minLongitude, BigDecimal maxLongitude,
                                                          BigDecimal minLatitude, BigDecimal maxLatitude,
                                                          BizType bizType, Integer cityId) {
        //TODO 缓存处理
        if (bizType == BizType.SELL) {
            return mapRepository.findSellCommunityListByMap(minLongitude,
                    maxLongitude,
                    minLatitude,
                    maxLatitude,
                    cityId);
        } else if (bizType == BizType.RENT) {
            return mapRepository.findRentCommunityListByMap(minLongitude,
                    maxLongitude,
                    minLatitude,
                    maxLatitude,
                    cityId);
        }
        return null;
    }
}

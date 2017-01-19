package com.lyun.estate.biz.housedict.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.housedict.repository.HouseRepository;
import com.lyun.estate.biz.spec.xiaoqu.entity.EstateMapResource;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.utilities.Assert;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jeffrey on 2017-01-10.
 */
@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    private LoadingCache<Long, City> cityCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES).build(new CacheLoader<Long, City>() {
                @Override
                public City load(Long key) throws Exception {
                    return Optional.ofNullable(houseRepository.findCity(key)).orElse(new City());
                }
            });

    private LoadingCache<Long, District> districtCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES).build(new CacheLoader<Long, District>() {
                @Override
                public District load(Long key) throws Exception {
                    return Optional.ofNullable(houseRepository.findDistrict(key)).orElse(new District());
                }
            });

    private LoadingCache<Long, SubDistrict> subDistrictCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES).build(new CacheLoader<Long, SubDistrict>() {
                @Override
                public SubDistrict load(Long key) throws Exception {
                    return Optional.ofNullable(houseRepository.findSubDistrict(key)).orElse(new SubDistrict());
                }
            });

    public City findCity(Long id) {
        try {
            return cityCache.get(id);
        } catch (ExecutionException e) {
            ExceptionUtil.catching(e);
        }
        return new City();
    }

    public District findDistrict(Long id) {
        try {
            return districtCache.get(id);
        } catch (ExecutionException e) {
            ExceptionUtil.catching(e);
        }
        return new District();
    }

    public SubDistrict findSubDistrict(Long id) {
        try {
            return subDistrictCache.get(id);
        } catch (ExecutionException e) {
            ExceptionUtil.catching(e);
        }
        return new SubDistrict();
    }

    public District findPrimaryDistrict(Long subDistrictId) {
        return Optional.ofNullable(houseRepository.findPrimaryDistrict(subDistrictId)).orElse(new District());
    }

    public List<EstateMapResource> findAllDistrictListByMap(int cityId) {
        //TODO 获取城市所有地区列表
        return null;
    }

    public List<EstateMapResource> findAllSubDistrictListByMap(int cityId) {
        //TODO 获取城市所有板块列表
        return null;
    }
}

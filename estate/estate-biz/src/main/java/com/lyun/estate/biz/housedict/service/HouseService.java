package com.lyun.estate.biz.housedict.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.housedict.repository.HouseRepository;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private LoadingCache<Long, District> districtCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<Long, District>() {
                @Override
                public District load(Long key) throws Exception {
                    return Optional.ofNullable(houseRepository.findDistrict(key)).orElse(new District());
                }
            });

    private LoadingCache<Long, SubDistrict> subDistrictCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<Long, SubDistrict>() {
                @Override
                public SubDistrict load(Long key) throws Exception {
                    return Optional.ofNullable(houseRepository.findSubDistrict(key)).orElse(new SubDistrict());
                }
            });

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
}

package com.lyun.estate.biz.housedict.service;

import com.lyun.estate.biz.housedict.def.LicenceType;
import com.lyun.estate.biz.housedict.entity.HouseLicence;
import com.lyun.estate.biz.housedict.spec.HouseDictService;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class HouseDictServiceImpl implements HouseDictService {

    @Override
    public HouseLicence register(LicenceType licenceType, Long communityId, Long buildingId, Long buildingUnitId,
                                 String houseNumber) {
        return null;
    }

    @Override
    public HouseLicence invalid(Long houseLicenceId) {
        return null;
    }

    @Override
    public HouseLicence find(LicenceType licenceType, Long communityId, Long buildingId, Long buildingUnitId,
                             String houseNumber) {
        return null;
    }
}

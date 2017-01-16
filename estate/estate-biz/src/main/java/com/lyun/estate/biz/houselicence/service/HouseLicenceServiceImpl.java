package com.lyun.estate.biz.houselicence.service;

import com.lyun.estate.biz.houselicence.def.LicenceType;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.spec.houselicence.HouseLicenceService;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class HouseLicenceServiceImpl implements HouseLicenceService {

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

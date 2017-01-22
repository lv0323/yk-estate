package com.lyun.estate.biz.houselicence.service;

import com.lyun.estate.biz.spec.fang.def.BizType;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.spec.houselicence.HouseLicenceService;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class HouseLicenceServiceImpl implements HouseLicenceService {

    @Override
    public HouseLicence register(BizType type, Long communityId, String building, String buildingUnit,
                                 String houseNo) {
        return null;
    }

    @Override
    public HouseLicence invalid(Long houseLicenceId) {
        return null;
    }

}

package com.lyun.estate.biz.houselicence.service;

import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2016-12-26.
 */
@Service
public class HouseLicenceService {

    public HouseLicence register(Long communityId, BizType bizType, Long buildingId, Long buildingUnitId,
                                 String houseNo) {

        return null;
    }

    public HouseLicence invalid(Long houseLicenceId) {
        return null;
    }

    public HouseLicence findActive(Long communityId, BizType bizType, Long buildingId, Long buildingUnitId,
                                   String houseNo) {

        return null;
    }
}

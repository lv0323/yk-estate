package com.lyun.estate.biz.spec.houselicence;

import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public interface HouseLicenceService {

    HouseLicence register(BizType type, Long communityId, String building, String buildingUnit,
                          String houseNo);

    HouseLicence invalid(Long houseLicenceId);

}

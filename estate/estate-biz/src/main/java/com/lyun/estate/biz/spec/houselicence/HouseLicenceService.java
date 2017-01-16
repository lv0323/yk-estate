package com.lyun.estate.biz.spec.houselicence;

import com.lyun.estate.biz.houselicence.def.LicenceType;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public interface HouseLicenceService {

    HouseLicence register(LicenceType licenceType, Long communityId, Long buildingId, Long buildingUnitId,
                          String houseNumber);

    HouseLicence invalid(Long houseLicenceId);

    HouseLicence find(LicenceType licenceType, Long communityId, Long buildingId, Long buildingUnitId,
                      String houseNumber);

}

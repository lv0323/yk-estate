package com.lyun.estate.biz.housedict.spec;

import com.lyun.estate.biz.housedict.def.LicenceType;
import com.lyun.estate.biz.housedict.entity.HouseLicence;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public interface HouseDictService {

    HouseLicence register(LicenceType licenceType, Long communityId, Long buildingId, Long buildingUnitId,
                          String houseNumber);

    HouseLicence invalid(Long houseLicenceId);

    HouseLicence find(LicenceType licenceType, Long communityId, Long buildingId, Long buildingUnitId,
                      String houseNumber);

}

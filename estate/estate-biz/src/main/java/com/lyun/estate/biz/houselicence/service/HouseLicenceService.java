package com.lyun.estate.biz.houselicence.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.houselicence.def.LicenceStatus;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.repository.HouseLicenceRepo;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Jeffrey on 2016-12-26.
 */
@Service
public class HouseLicenceService {

    private HouseLicenceRepo repo;

    public HouseLicenceService(HouseLicenceRepo repo) {
        this.repo = repo;
    }

    public HouseLicence register(Long communityId, BizType bizType, Long buildingId, Long buildingUnitId,
                                 String houseNo) {
        houseNo = Optional.ofNullable(houseNo).map(n -> n.replace(" ", "")).orElse(null);
        ExceptionUtil.checkNotNull("楼盘编号", communityId);
        ExceptionUtil.checkNotNull("业务类型", bizType);
        ExceptionUtil.checkNotNull("楼栋号", buildingId);
        ExceptionUtil.checkNotNull("单元号", buildingUnitId);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(houseNo), "房号", houseNo);
        HouseLicence active = findActive(communityId, bizType, buildingId, buildingUnitId, houseNo);
        if (active != null) {
            throw new EstateException(ExCode.LICENCE_HOUSE_EXISTED, active.getId());
        } else {
            HouseLicence houseLicence = new HouseLicence().setCommunityId(communityId)
                    .setBizType(bizType).setBuildingId(buildingId).setBuildingUnitId(buildingUnitId)
                    .setHouseNo(houseNo).setStatus(LicenceStatus.ACTIVE);
            if (repo.save(houseLicence) > 0) {
                return repo.findOne(houseLicence.getId());
            } else {
                throw new EstateException(ExCode.CREATE_FAIL, "授权", "");
            }
        }
    }

    public HouseLicence invalid(Long id) {
        ExceptionUtil.checkNotNull("授权编号", id);
        return repo.invalid(id);
    }

    public HouseLicence findActive(Long communityId, BizType bizType, Long buildingId, Long buildingUnitId,
                                   String houseNo) {
        houseNo = Optional.ofNullable(houseNo).map(n -> n.replace(" ", "")).orElse(null);
        ExceptionUtil.checkNotNull("楼盘编号", communityId);
        ExceptionUtil.checkNotNull("业务类型", bizType);
        ExceptionUtil.checkNotNull("楼栋号", buildingId);
        ExceptionUtil.checkNotNull("单元号", buildingUnitId);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(houseNo), "房号", houseNo);
        return repo.findActive(communityId, bizType, buildingId, buildingUnitId, houseNo);
    }
}

package com.lyun.estate.biz.houselicence.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.service.HouseDictService;
import com.lyun.estate.biz.houselicence.def.LicenceStatus;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.entity.HouseLicenceDTO;
import com.lyun.estate.biz.houselicence.repository.HouseLicenceRepo;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Jeffrey on 2016-12-26.
 */
@Service
public class HouseLicenceService {

    private HouseLicenceRepo repo;

    private HouseDictService houseDictService;

    public HouseLicenceService(HouseLicenceRepo repo, HouseDictService houseDictService) {
        this.repo = repo;
        this.houseDictService = houseDictService;
    }

    @Transactional
    public HouseLicence register(Long communityId, BizType bizType, Long buildingId, Long buildingUnitId,
                                 String houseNo) {
        houseNo = Optional.ofNullable(houseNo).map(n -> n.replace(" ", "")).orElse(null);
        ExceptionUtil.checkNotNull("楼盘编号", communityId);
        ExceptionUtil.checkNotNull("业务类型", bizType);
        ExceptionUtil.checkNotNull("楼栋号", buildingId);
        ExceptionUtil.checkNotNull("单元号", buildingUnitId);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(houseNo), "房号", houseNo);

        Building building = houseDictService.findBuildingForUpdate(buildingId);
        if (building == null || !Objects.equals(building.getCommunityId(), communityId)) {
            throw new EstateException(ExCode.LICENCE_LOCATION_ERROR);
        }
        List<BuildingUnit> buildingUnits = houseDictService.findBuildingUnitsByBuildingId(buildingId);
        boolean unitExisted = buildingUnits.stream()
                .anyMatch(t -> Objects.equals(t.getId(), buildingUnitId));
        if (!unitExisted) {
            throw new EstateException(ExCode.LICENCE_LOCATION_ERROR);
        }
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
                throw new EstateException(ExCode.CREATE_FAIL, "房源授权", houseLicence.toString());
            }
        }
    }

    public HouseLicence invalid(Long id) {
        ExceptionUtil.checkNotNull("房源授权编号", id);
        if (repo.invalid(id) > 0) {
            return repo.findOne(id);
        } else {
            throw new EstateException(ExCode.UPDATE_FAIL, "房源授权", "id:" + id);
        }
    }

    public HouseLicence active(long id) {
        HouseLicence houseLicence = repo.findOne(id);
        if (houseLicence == null) {
            throw new EstateException(ExCode.NOT_FOUND, id, "房源授权编号");
        }
        if (Objects.equals(LicenceStatus.ACTIVE, houseLicence.getStatus())) {
            return houseLicence;
        }
        HouseLicence active = findActive(houseLicence.getCommunityId(),
                houseLicence.getBizType(),
                houseLicence.getBuildingId(),
                houseLicence.getBuildingUnitId(),
                houseLicence.getHouseNo());
        if (active != null) {
            throw new EstateException(ExCode.LICENCE_HOUSE_EXISTED, active.getId());
        }
        if (repo.active(id) > 0) {
            return repo.findOne(id);
        }
        throw new EstateException(ExCode.UPDATE_FAIL, "房源授权", "id:" + id);
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

    public HouseLicenceDTO findOne(Long id) {
        ExceptionUtil.checkNotNull("房源授权编号", id);
        HouseLicenceDTO licenceDTO = repo.findDTOById(id);
        if (Objects.nonNull(licenceDTO)) {
            licenceDTO.buildLocation();
        }
        return licenceDTO;
    }
}

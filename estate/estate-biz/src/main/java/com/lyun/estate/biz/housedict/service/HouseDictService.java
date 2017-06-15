package com.lyun.estate.biz.housedict.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.housedict.domain.DomainHouseCount;
import com.lyun.estate.biz.housedict.domain.XiaoQuOption;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.entity.DistrictRel;
import com.lyun.estate.biz.housedict.repository.HouseDictRepo;
import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Service
public class HouseDictService {
    @Autowired
    private HouseDictRepo houseDictRepo;

    @Autowired
    private MgtXiaoQuService mgtXiaoQuService;


    @Transactional
    public Building createBuilding(Long xiaoQuId, String name, Integer floors, Integer stairs, Integer houses,
                                   String description, List<String> unitNames, Long companyId, Long operatorId) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        ExceptionUtil.checkIllegal(!StringUtils.isEmpty(name), "楼栋名", name);
        ExceptionUtil.checkIllegal(floors != null && floors > 0, "总楼层", floors);
        ExceptionUtil.checkIllegal(stairs != null && stairs > 0, "梯数", stairs);
        ExceptionUtil.checkIllegal(houses != null && houses > 0, "户数", houses);
        ExceptionUtil.checkNotNull("公司编号", companyId);
        ExceptionUtil.checkNotNull("操作者编号", operatorId);
        XiaoQu xiaoQu = mgtXiaoQuService.findOne(xiaoQuId);
        if (xiaoQu == null) {
            throw new EstateException(ExCode.CREATE_FAIL, "楼栋", "小区编号:" + xiaoQuId);
        }

        Building building = new Building()
                .setCommunityId(xiaoQu.getCommunityId())
                .setName(name)
                .setFloors(floors)
                .setStairs(stairs)
                .setHouses(houses)
                .setDescription(description)
                .setCompanyId(companyId)
                .setCreateById(operatorId);
        if (houseDictRepo.saveBuilding(building) > 0) {
            if (!CollectionUtils.isEmpty(unitNames)) {
                unitNames.forEach(t -> createBuildingUnit(building.getId(), t, operatorId));
            }
            return findBuildingAndUnits(building.getId());
        } else {
            throw new EstateException(ExCode.CREATE_FAIL, "楼栋", "小区编号:" + xiaoQuId);
        }

    }

    public BuildingUnit createBuildingUnit(Long buildingId, String unitName, Long operatorId) {
        ExceptionUtil.checkNotNull("楼栋编号", buildingId);
        ExceptionUtil.checkIllegal(!StringUtils.isEmpty(unitName), "楼栋名", unitName);
        ExceptionUtil.checkNotNull("操作者编号", operatorId);

        BuildingUnit buildingUnit = new BuildingUnit()
                .setBuildingId(buildingId)
                .setUnitName(unitName)
                .setCreateById(operatorId);

        if (houseDictRepo.saveBuildingUnit(buildingUnit) > 0) {
            return houseDictRepo.findBuildingUnit(buildingUnit.getId());
        } else {
            throw new EstateException(ExCode.CREATE_FAIL, "单元", "楼栋编号:" + buildingId);
        }
    }

    public List<Building> findBuildingsByXiaoQuId(Long xiaoQuId, long companyId) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        XiaoQu xiaoQu = mgtXiaoQuService.findOne(xiaoQuId);
        if (xiaoQu == null) {
            return new ArrayList<>();
        }
        List<Building> buildings = houseDictRepo.findBuildingByCommunityIdAndCompanyId(xiaoQu.getCommunityId(),
                companyId);
        if (buildings != null) {
            buildings.forEach(b -> b.setUnits(houseDictRepo.findBuildingUnitByBuildingId(b.getId())));
        }
        return buildings;
    }


    public Building findBuildingAndUnits(Long buildingId) {
        ExceptionUtil.checkNotNull("楼栋编号", buildingId);
        Building building = houseDictRepo.findBuilding(buildingId);
        if (building != null) {
            List<BuildingUnit> units = houseDictRepo.findBuildingUnitByBuildingId(building.getId());
            return building.setUnits(units);
        } else {
            return null;
        }
    }

    public List<BuildingUnit> findBuildingUnitsByBuildingId(Long buildingId) {
        ExceptionUtil.checkNotNull("楼栋编号", buildingId);
        return houseDictRepo.findBuildingUnitByBuildingId(buildingId);
    }

    public List<XiaoQuOption> top20XiaoQuOptions(Long cityId) {
        return houseDictRepo.findTop20XiaoQuOptions(cityId);
    }

    public Building findBuildingAndUnit(Long buildingId, Long buildingUnitId) {
        ExceptionUtil.checkNotNull("楼栋编号", buildingId);
        ExceptionUtil.checkNotNull("单元编号", buildingUnitId);
        Building building = houseDictRepo.findBuilding(buildingId);
        if (building != null) {
            BuildingUnit buildingUnit = houseDictRepo.findBuildingUnit(buildingUnitId);
            if (buildingUnit != null && Objects.equals(buildingUnit.getBuildingId(), buildingId)) {
                building.setUnits(Lists.newArrayList(buildingUnit));
            }
        }
        return building;
    }

    public boolean houseCountForSubDistrictAndDistrict(Long subDistrictId) {
        DomainHouseCount subDistrictHouseCount = houseDictRepo.houseCountForSubDistrict(subDistrictId);

        houseDictRepo.updateSubDistrictHouseCount(subDistrictHouseCount);

        List<DistrictRel> districtRels = houseDictRepo.findDistrictRel(subDistrictId);

        districtRels.forEach(it -> {
            DomainHouseCount districtHouseCount = houseDictRepo.houseCountForDistricts(it.getDistrictId());
            houseDictRepo.updateDistrictHouseCount(districtHouseCount);
        });
        return true;
    }

    @Transactional
    public Building updateBuilding(Long buildingId, String name, Integer floors, Integer stairs, Integer houses,
                                   String description, List<String> unitNames, Long companyId, Long operatorId) {

        //update building info
        houseDictRepo.updateBuildingInfo(buildingId, name, floors, stairs, houses, description, operatorId);

        //delete old building unit
        houseDictRepo.deleteOldBuildingUnit(buildingId, operatorId);

        //create new building unit
        if (!CollectionUtils.isEmpty(unitNames)) {
            unitNames.forEach(t -> createBuildingUnit(buildingId, t, operatorId));
        }
        return findBuildingAndUnits(buildingId);
    }

    public boolean deleteBuilding(Long buildingId, Long operatorId) {
        return houseDictRepo.deleteBuilding(buildingId, operatorId) > 0;
    }

    public Building findBuildingForUpdate(Long buildingId) {
        ExceptionUtil.checkNotNull("楼栋编号", buildingId);
        return houseDictRepo.findBuildingForUpdate(buildingId);
    }
}

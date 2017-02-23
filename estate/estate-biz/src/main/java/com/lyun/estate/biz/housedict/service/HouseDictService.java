package com.lyun.estate.biz.housedict.service;

import com.lyun.estate.biz.housedict.domain.XiaoQuOption;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.repository.HouseDictRepo;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.service.MgtXiaoQuService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Service
public class HouseDictService {
    @Autowired
    private HouseDictRepo houseDictRepo;

    @Autowired
    private MgtXiaoQuService mgtXiaoQuService;


    public Building createBuilding(Long xiaoQuId, String name, Integer floors, Integer stairs, Integer houses,
                                   String description, Long operatorId) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        ExceptionUtil.checkIllegal(!StringUtils.isEmpty(name), "楼栋名", name);
        ExceptionUtil.checkIllegal(floors != null && floors > 0, "总楼层", floors);
        ExceptionUtil.checkIllegal(stairs != null && stairs > 0, "梯数", stairs);
        ExceptionUtil.checkIllegal(houses != null && houses > 0, "户数", houses);
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
                .setCreateById(operatorId);
        if (houseDictRepo.saveBuilding(building) > 0) {
            return findBuilding(building.getId());
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

    public List<Building> findBuildingsByXiaoQuId(Long xiaoQuId) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        XiaoQu xiaoQu = mgtXiaoQuService.findOne(xiaoQuId);
        if (xiaoQu == null) {
            return new ArrayList<>();
        }
        List<Building> buildings = houseDictRepo.findBuildingByCommunityId(xiaoQu.getCommunityId());
        if (buildings != null) {
            buildings.forEach(b -> b.setUnits(houseDictRepo.findBuildingUnitByBuildingId(b.getId())));
        }
        return buildings;
    }


    public Building findBuilding(Long buildingId) {
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

    public List<XiaoQuOption> xiaoQuOptions(Long cityId) {
        return houseDictRepo.findXiaoQuOptions(cityId);
    }
}
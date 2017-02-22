package com.lyun.estate.mgt.housedict.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.housedict.domain.XiaoQuOption;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.service.HouseDictService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Service
public class HouseDictMgtService {

    private MgtContext mgtContext;

    private HouseDictService houseDictService;

    public HouseDictMgtService(MgtContext mgtContext,
                               HouseDictService houseDictService) {
        this.mgtContext = mgtContext;
        this.houseDictService = houseDictService;
    }

    public Building createBuilding(Long xiaoQuId, String name, Integer floors, Integer stairs, Integer houses,
                                   String description) {

        return houseDictService.createBuilding(xiaoQuId,
                name,
                floors,
                stairs,
                houses,
                description,
                mgtContext.getOperator().getId());
    }

    @Transactional
    public Integer createBuildingUnit(Long buildingId, List<String> unitNames) {
        final Integer[] count = {0};
        if (unitNames != null) {
            unitNames.forEach(unitName -> {
                        houseDictService.createBuildingUnit(buildingId,
                                unitName,
                                mgtContext.getOperator().getId());

                        count[0]++;
                    }
            );
        }
        return count[0];
    }

    public List<Building> findBuildingsByXiaoQuId(Long communityId) {
        return houseDictService.findBuildingsByXiaoQuId(communityId);
    }


    public Building findBuilding(Long buildingId) {
        return houseDictService.findBuilding(buildingId);
    }

    public List<BuildingUnit> findBuildingUnitsByBuildingId(Long buildingId) {
        return houseDictService.findBuildingUnitsByBuildingId(buildingId);
    }


    public List<XiaoQuOption> xiaoQuOptions() {
        return houseDictService.xiaoQuOptions(mgtContext.getOperator().getCityId());
    }
}

package com.lyun.estate.mgt.housedict.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.housedict.domain.XiaoQuOption;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.service.HouseDictService;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Service
public class HouseDictMgtService {

    private MgtContext mgtContext;

    private HouseDictService houseDictService;

    private KeywordService keywordService;

    public HouseDictMgtService(MgtContext mgtContext,
                               HouseDictService houseDictService,
                               KeywordService keywordService) {
        this.mgtContext = mgtContext;
        this.houseDictService = houseDictService;
        this.keywordService = keywordService;
    }

    public Building createBuilding(Long xiaoQuId, String name, Integer floors, Integer stairs, Integer houses,
                                   String description) {

        return houseDictService.createBuilding(xiaoQuId,
                name,
                floors,
                stairs,
                houses,
                description,
                mgtContext.getOperator().getCompanyId(),
                mgtContext.getOperator().getId());
    }

    @Transactional
    public Integer createBuildingUnit(Long buildingId, List<String> unitNames) {
        final Integer[] count = {0};
        if (unitNames != null) {
            unitNames.stream().filter(t -> !Strings.isNullOrEmpty(t)).forEach(unitName -> {
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

        return houseDictService.findBuildingsByXiaoQuId(communityId, mgtContext.getOperator().getCompanyId());
    }


    public Building findBuilding(Long buildingId) {
        return houseDictService.findBuildingAndUnits(buildingId);
    }

    public List<BuildingUnit> findBuildingUnitsByBuildingId(Long buildingId) {
        return houseDictService.findBuildingUnitsByBuildingId(buildingId);
    }


    public List<XiaoQuOption> xiaoQuOptions(String keyword) {
        if (Strings.isNullOrEmpty(keyword)) {
            return houseDictService.top20XiaoQuOptions(mgtContext.getOperator().getCityId());
        } else {
            return keywordService.findContain(keyword,
                    mgtContext.getOperator().getCityId(),
                    Lists.newArrayList(DomainType.XIAO_QU),
                    20)
                    .stream().map(t -> new XiaoQuOption().setXiaoQuId(t.getId()).setXiaoQuName(t.getName())).collect(
                            Collectors.toList());
        }
    }
}

package com.lyun.estate.mgt.housedict.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.housedict.domain.XiaoQuOption;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.service.HouseDictService;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.support.settings.SettingProvider;
import com.lyun.estate.biz.support.settings.def.NameSpace;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Service
public class HouseDictMgtService {

    @Autowired
    private MgtContext mgtContext;

    @Autowired
    private HouseDictService houseDictService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private SettingProvider settingProvider;

    private Long platformCompanyId;

    @PostConstruct
    private void init() {
        platformCompanyId = Long.valueOf(settingProvider.find(NameSpace.CONFIG, "PLATFORM_COMPANY_ID").getValue());
    }


    @Transactional
    public Building createBuilding(Long xiaoQuId, String name, Integer floors, Integer stairs, Integer houses,
                                   String description, List<String> unitNames) {
        permissionCheckService.checkExist(Permission.CREATE_BUILDING);

        Building building = houseDictService.createBuilding(xiaoQuId,
                name,
                floors,
                stairs,
                houses,
                description,
                unitNames,
                Optional.ofNullable(platformCompanyId).orElse(mgtContext.getOperator().getCompanyId()),
                mgtContext.getOperator().getId());

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.BUILDING, building.getId(), DomainType.BUILDING,
                        AuditHelper.operatorName(mgtContext) + "新增了栋座信息【" + building + "】")
        );

        return building;
    }

    @Transactional
    public Integer createBuildingUnit(Long buildingId, List<String> unitNames) {

        permissionCheckService.checkExist(Permission.CREATE_BUILDING);

        final Integer[] count = {0};
        if (unitNames != null) {
            unitNames.stream().filter(t -> !Strings.isNullOrEmpty(t)).forEach(unitName -> {
                        houseDictService.createBuildingUnit(buildingId,
                                unitName,
                                mgtContext.getOperator().getId());

                        count[0]++;
                    }
            );
            auditService.save(
                    AuditHelper.build(mgtContext, AuditSubject.BUILDING, buildingId, DomainType.BUILDING,
                            AuditHelper.operatorName(mgtContext) + "为编号【" + buildingId + "】的栋座新增了单元【" + unitNames.toString() + "】")
            );
        }
        return count[0];
    }

    public List<Building> findBuildingsByXiaoQuId(Long communityId) {
        return houseDictService.findBuildingsByXiaoQuId(communityId,
                Optional.ofNullable(platformCompanyId).orElse(mgtContext.getOperator().getCompanyId()));
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

    @Transactional
    public Building updateBuilding(Long buildingId, String name, Integer floors, Integer stairs, Integer houses,
                                   String description, List<String> unitNames) {

        permissionCheckService.checkExist(Permission.MODIFY_BUILDING);

        Building building = houseDictService.updateBuilding(buildingId,
                name,
                floors,
                stairs,
                houses,
                description,
                unitNames,
                Optional.ofNullable(platformCompanyId).orElse(mgtContext.getOperator().getCompanyId()),
                mgtContext.getOperator().getId());

        permissionCheckService.checkCompany(building.getCompanyId());

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.BUILDING, building.getId(), DomainType.BUILDING,
                        AuditHelper.operatorName(mgtContext) + "修改了栋座信息【" + building + "】")
        );
        return building;
    }

    @Transactional
    public boolean deleteBuilding(Long buildingId) {
        permissionCheckService.checkExist(Permission.DEL_BUILDING);

        boolean result = houseDictService.deleteBuilding(buildingId, mgtContext.getOperator().getId());
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.BUILDING, buildingId, DomainType.BUILDING,
                        AuditHelper.operatorName(mgtContext) + "删除了编号为【" + buildingId + "】的栋座")
        );
        return result;
    }
}

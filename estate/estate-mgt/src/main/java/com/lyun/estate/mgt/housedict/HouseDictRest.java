package com.lyun.estate.mgt.housedict;

import com.lyun.estate.biz.housedict.domain.XiaoQuOption;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.mgt.housedict.service.HouseDictMgtService;
import com.lyun.estate.mgt.supports.CommonResp;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@RestController
@RequestMapping("api/house-dict")
public class HouseDictRest {

    private HouseDictMgtService houseDictMgtService;

    public HouseDictRest(HouseDictMgtService houseDictMgtService) {
        this.houseDictMgtService = houseDictMgtService;
    }

    @PostMapping("building")
    public Building createBuilding(@RequestParam Long xiaoQuId,
                                   @RequestParam String name,
                                   @RequestParam Integer floors,
                                   @RequestParam Integer stairs,
                                   @RequestParam Integer houses,
                                   @RequestParam(required = false) String description,
                                   @RequestParam(required = false) List<String> unitNames) {
        return houseDictMgtService.createBuilding(xiaoQuId,
                name,
                floors,
                stairs,
                houses,
                description, unitNames);
    }

    @PostMapping("building/update")
    public Building updateBuilding(@RequestParam Long buildingId,
                                   @RequestParam String name,
                                   @RequestParam Integer floors,
                                   @RequestParam Integer stairs,
                                   @RequestParam Integer houses,
                                   @RequestParam String description,
                                   @RequestParam List<String> unitNames) {
        return houseDictMgtService.updateBuilding(buildingId,
                name,
                floors,
                stairs,
                houses,
                description, unitNames);
    }


    @PostMapping("building/delete")
    public CommonResp deleteBuilding(@RequestParam Long buildingId) {
        return houseDictMgtService.deleteBuilding(buildingId) ? CommonResp.succeed() : CommonResp.failed();
    }

    @PostMapping("building-unit")
    public Object createBuildingUnit(@RequestParam Long buildingId,
                                     @RequestParam List<String> unitNames) {
        return new RestResponse().add("counts", houseDictMgtService.createBuildingUnit(buildingId, unitNames)).get();
    }

    @GetMapping("buildings")
    public List<Building> findBuildingsByXiaoQuId(@RequestParam Long xiaoQuId) {
        return houseDictMgtService.findBuildingsByXiaoQuId(xiaoQuId);
    }

    @GetMapping("buildings/{id}")
    public Building findBuilding(@PathVariable Long id) {
        return houseDictMgtService.findBuilding(id);
    }

    @GetMapping("building-unit")
    public List<BuildingUnit> findBuildingUnitsByBuildingId(@RequestParam Long buildingId) {
        return houseDictMgtService.findBuildingUnitsByBuildingId(buildingId);
    }

    @GetMapping("xiao-qu-option")
    public List<XiaoQuOption> keywords(@RequestParam String keyword) {
        return houseDictMgtService.xiaoQuOptions(keyword);
    }

}

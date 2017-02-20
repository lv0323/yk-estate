package com.lyun.estate.mgt.housedict;

import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.mgt.housedict.service.HouseDictMgtService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@RestController("house-dict")
public class HouseDictRest {

    private HouseDictMgtService houseDictMgtService;

    public HouseDictRest(HouseDictMgtService houseDictMgtService) {
        this.houseDictMgtService = houseDictMgtService;
    }

    @GetMapping("keywords")
    public List<KeywordBean> keywords(@RequestParam String keywords) {
        return houseDictMgtService.keywords(keywords);
    }

    @PostMapping("building")
    public Building createBuilding(@RequestParam Long communityId,
                                   @RequestParam String name,
                                   @RequestParam Integer floors,
                                   @RequestParam Integer stairs,
                                   @RequestParam Integer houses,
                                   @RequestParam String description) {
        return houseDictMgtService.createBuilding(communityId,
                name,
                floors,
                stairs,
                houses,
                description);
    }

    @PostMapping("building-unit")
    public Object createBuildingUnit(@RequestParam Long buildingId,
                                     @RequestParam List<String> unitNames) {
        return new RestResponse().add("counts", houseDictMgtService.createBuildingUnit(buildingId, unitNames)).get();
    }

    @GetMapping("buildings")
    public List<Building> findBuildingsByCommunityId(@RequestParam Long communityId) {
        return houseDictMgtService.findBuildingsByCommunityId(communityId);
    }

    @GetMapping("buildings/{id}")
    public Building findBuilding(@PathVariable Long id) {
        return houseDictMgtService.findBuilding(id);
    }

    @GetMapping("building-unit")
    public List<BuildingUnit> findBuildingUnitsByBuildingId(@RequestParam Long buildingId) {
        return houseDictMgtService.findBuildingUnitsByBuildingId(buildingId);
    }

}

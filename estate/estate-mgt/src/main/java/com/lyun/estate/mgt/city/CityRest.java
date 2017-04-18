package com.lyun.estate.mgt.city;

import com.lyun.estate.biz.housedict.domain.DistrictWithSubs;
import com.lyun.estate.biz.housedict.service.CityService;
import com.lyun.estate.mgt.context.MgtContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cities/")
public class CityRest {

    private final CityService service;

    private MgtContext mgtContext;

    public CityRest(CityService service, MgtContext mgtContext) {
        this.service = service;
        this.mgtContext = mgtContext;
    }

    @GetMapping
    public Object cites() {
        return service.findCities();
    }

    @GetMapping("districts")
    public Object district(@RequestParam(required = false) Long id) {
        return service.findOrderedDistricts(
                Optional.ofNullable(id).orElse(mgtContext.getOperator().getCityId()
                ));
    }

    @GetMapping("sub-districts")
    public Object subDistrict(@RequestParam Long id) {
        return service.findOrderedSubDistricts(id);
    }

    @GetMapping("districts-with-subs")
    public List<DistrictWithSubs> districtWithSubs(@RequestParam Long cityId) {
        return service.findOrderedDistrictWithSubs(cityId);
    }
}

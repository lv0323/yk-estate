package com.lyun.estate.mgt.city;

import com.lyun.estate.biz.housedict.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/department/district")
public class CityRest {

    private final CityService service;

    public CityRest(CityService service) {
        this.service = service;
    }

    @GetMapping("cites")
    public Object cites() {
        return service.findCities();
    }

    @GetMapping("districts")
    public Object district(@RequestParam Long id) {
        return service.findOrderedDistricts(id);
    }

    @GetMapping("sub-districts")
    public Object subDistrict(@RequestParam Long id) {
        return service.findOrderedSubDistricts(id);
    }
}

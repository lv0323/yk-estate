package com.lyun.estate.mgt.department;

import com.lyun.estate.biz.housedict.service.DistrictService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/department/district")
public class DistrictController {

    private final DistrictService service;

    public DistrictController(DistrictService service) {
        this.service = service;
    }

    @GetMapping("cites")
    public Object cites() {
        return service.findCities();
    }

    @GetMapping("districts")
    public Object district(@RequestParam Long id) {
        return service.findDistrictsByCityId(id);
    }

    @GetMapping("sub-districts")
    public Object subDistrict(@RequestParam Long id) {
        return service.findSubDistrictsByDistrictId(id);
    }
}
package com.lyun.estate.mgt.position.controller;

import com.lyun.estate.mgt.position.entity.Position;
import com.lyun.estate.mgt.position.service.PositionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/position")
public class PositionController {

    private final PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(@RequestBody Position position) {
        return service.create(position);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        return service.deleteById(id);
    }

    @PostMapping("edit")
    public Object edit(@RequestBody Position position) {
        return service.update(position);
    }

    @GetMapping("query")
    public Object query(@RequestParam Long companyId) {
        return service.selectByCompanyId(companyId);
    }
}

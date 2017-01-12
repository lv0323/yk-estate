package com.lyun.estate.mgt.position;

import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.service.PositionService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/position")
public class PositionController {

    private final PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(Position position, @SessionAttribute Employee employee) {
        Objects.requireNonNull(position).setCompanyId(employee.getCompanyId());
        return service.create(position);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id, @SessionAttribute Employee employee) {
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Position position, @SessionAttribute Employee employee) {
        return service.update(position);
    }

    @GetMapping("query")
    public Object query(@SessionAttribute Employee employee) {
        return service.selectByCompanyId(employee.getCompanyId());
    }
}

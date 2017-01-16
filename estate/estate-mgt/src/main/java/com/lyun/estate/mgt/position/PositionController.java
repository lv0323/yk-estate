package com.lyun.estate.mgt.position;

import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.service.PositionService;
import com.lyun.estate.mgt.employee.LoginEmployee;
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
    public Object add(Position position, @SessionAttribute LoginEmployee employee) {
        Objects.requireNonNull(position).setCompanyId(employee.getCompanyId());
        return service.create(position);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id, @SessionAttribute LoginEmployee employee) {
        Position position = service.selectById(id);
        if (position == null)
            return new RestResponse().add("ret", true).get();
        if (!Objects.equals(position.getCompanyId(), employee.getCompanyId()))
            return new RestResponse().add("ret", false).get();
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Position position, @SessionAttribute LoginEmployee employee) {
        return service.update(position);
    }

    @GetMapping("query")
    public Object query(@SessionAttribute LoginEmployee employee) {
        return service.selectByCompanyId(employee.getCompanyId());
    }
}

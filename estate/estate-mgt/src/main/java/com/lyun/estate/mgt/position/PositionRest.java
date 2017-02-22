package com.lyun.estate.mgt.position;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.mgt.position.service.PositionMgtService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/position")
public class PositionRest {

    private final PositionMgtService service;

    public PositionRest(PositionMgtService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(Position position) {
        return service.create(position);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Position position) {
        return service.update(position);
    }

    @GetMapping("query")
    public PageList<Position> query(@RequestHeader("X-PAGING") PageBounds pageBounds) {
        return service.listByPageBounds(pageBounds);
    }

    @GetMapping("query-all")
    public List<Position> query() {
        return service.listAllByCompanyId();
    }
}

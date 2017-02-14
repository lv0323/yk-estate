package com.lyun.estate.mgt.position;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.service.PositionService;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/position")
public class PositionRest {

    private final PositionService service;

    @Autowired
    private MgtContext mgtContext;

    public PositionRest(PositionService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(Position position) {
        Objects.requireNonNull(position).setCompanyId(mgtContext.getOperator().getCompanyId());
        return service.create(position);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        Position position = service.selectById(id);
        if (position == null)
            return new RestResponse().add("ret", true).get();
        if (!Objects.equals(position.getCompanyId(), mgtContext.getOperator().getCompanyId()))
            return new RestResponse().add("ret", false).get();
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Position position) {
        return service.update(position);
    }

    @GetMapping("query")
    public PageList<Position> query(@RequestHeader("X-PAGING") PageBounds pageBounds) {
        return service.selectByCompanyId(mgtContext.getOperator().getCompanyId(), pageBounds);
    }
}

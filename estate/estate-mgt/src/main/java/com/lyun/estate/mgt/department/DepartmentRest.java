package com.lyun.estate.mgt.department;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.mgt.department.service.DepartmentMgtService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/department")
public class DepartmentRest {

    private final DepartmentMgtService service;

    public DepartmentRest(DepartmentMgtService service) {
        this.service = service;
    }

    @PostMapping(value = "add")
    public Object add(Department department) {
        return service.create(department);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Department department) {
        Objects.requireNonNull(department);
        return service.updateInfo(department);
    }

    @GetMapping("query")
    public PageList<DepartmentDTO> query(@RequestHeader("X-PAGING") PageBounds pageBounds) {
        return service.listByPageBounds(pageBounds);
    }

    @GetMapping("query-all")
    public List<DepartmentDTO> queryAll(@RequestParam(required = false) Long compnayId) {
        return service.listSortedByCompanyId(compnayId);
    }

    @GetMapping("changeParent")
    public Department changParent(@RequestParam Long departmentId, @RequestParam Long parentId) {
        return service.changeParent(departmentId, parentId);
    }

    @GetMapping("/{departmentId}")
    Department findOne(@PathVariable Long departmentId) {
        return service.selectById(departmentId);
    }
}

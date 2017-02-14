package com.lyun.estate.mgt.department;

import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.mgt.employee.LoginEmployee;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/department")
public class DepartmentRest {

    private final DepartmentService service;

    public DepartmentRest(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(Department department, @SessionAttribute LoginEmployee employee) {
        Objects.requireNonNull(department).setCompanyId(employee.getCompanyId());
        return service.create(department);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id, @SessionAttribute LoginEmployee employee) {
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Department department, @SessionAttribute LoginEmployee employee) {
        Objects.requireNonNull(department).setCompanyId(employee.getCompanyId());
        return service.updateInfo(department);
    }

    @GetMapping("query")
    public List<Department> query(@SessionAttribute LoginEmployee employee) {
        return service.selectByCompanyId(employee.getCompanyId());
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

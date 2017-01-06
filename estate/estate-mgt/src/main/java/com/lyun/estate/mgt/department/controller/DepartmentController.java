package com.lyun.estate.mgt.department.controller;

import com.lyun.estate.mgt.department.entity.Department;
import com.lyun.estate.mgt.department.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(@RequestBody Department department) {
        return service.create(department);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        return service.deleteById(id);
    }

    @PostMapping("edit")
    public Object edit(@RequestBody Department department) {
        return service.update(department);
    }

    @GetMapping("query")
    public Object query(@RequestParam Long companyId) {
        return service.selectByCompanyId(companyId);
    }
}

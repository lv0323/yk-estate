package com.lyun.estate.mgt.department;

import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.mgt.supports.RestResponse;
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
        return new RestResponse().add("ret", service.deleteById(id)).get();
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

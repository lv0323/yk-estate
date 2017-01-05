package com.lyun.estate.mgt.department.controller;

import com.lyun.estate.mgt.department.RestResponse;
import com.lyun.estate.mgt.department.entity.Department;
import com.lyun.estate.mgt.department.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(@RequestBody Department department) {
        try {
            service.insert(department);
            return new RestResponse().add("ret", true).get();
        } catch (Exception e) {
            return new RestResponse().add("ret", false).add("message", e.getMessage()).get();
        }
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        try {
            service.deleteById(id);
            return new RestResponse().add("ret", true).get();
        } catch (Exception e) {
            return new RestResponse().add("ret", false).add("message", e.getMessage()).get();
        }
    }

    @PostMapping("edit")
    public Object edit(@RequestBody Department department) {
        try {
            service.update(department);
            return new RestResponse().add("ret", true).get();
        } catch (Exception e) {
            return new RestResponse().add("ret", false).add("message", e.getMessage()).get();
        }
    }

    @GetMapping("query")
    public Object query(@RequestParam Long companyId) {
        try {
            List<Department> departmentList = service.selectByCompanyId(companyId);
            return new RestResponse().add("ret", true)
                    .add("result", new RestResponse().add("size", departmentList.size()).add("departments", departmentList).get()).get();
        } catch (Exception e) {
            return new RestResponse().add("ret", false).add("message", e.getMessage()).get();
        }
    }
}

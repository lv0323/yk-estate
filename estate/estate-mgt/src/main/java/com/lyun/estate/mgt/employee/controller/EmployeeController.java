package com.lyun.estate.mgt.employee.controller;

import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.mgt.employee.entity.Employee;
import com.lyun.estate.mgt.employee.service.EmployeeService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("create")
    public Object create(@RequestBody @Valid Employee employee, BindingResult result) {
        if (result.hasErrors())
            throw new ValidateException("参数不合法", result.getAllErrors());
        return service.create(employee);
    }

    @GetMapping("query")
    public Object query(@RequestParam Long companyId) {
        return service.selectByCompanyId(companyId);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(@RequestBody @Valid Employee employee, BindingResult result) {
        if (result.hasErrors())
            throw new ValidateException("参数不合法", result.getAllErrors());
        return service.update(employee);
    }
}

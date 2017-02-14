package com.lyun.estate.mgt.employee;

import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("api/employee")
public class EmployeeRest {

    private final EmployeeService employeeService;
    @Autowired
    private MgtContext mgtContext;

    @SuppressWarnings("unchecked")
    public EmployeeRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("create")
    public Object create(Employee entity) {
        Objects.requireNonNull(entity).setCompanyId(mgtContext.getOperator().getCompanyId());
        return employeeService.create(entity);
    }

    @GetMapping("query")
    public Object query() {
        return employeeService.selectByCompanyId(mgtContext.getOperator().getCompanyId());
    }

    @PostMapping("edit")
    public Object edit(Employee entity) {
        return employeeService.update(entity);
    }

    @GetMapping("avatar")
    public Object avatar(@RequestParam Long id) {
        return new RestResponse().add("url", employeeService.getAvatar(id)).get();
    }

    @PostMapping("avatar")
    public Object avatar(@RequestParam Long id, @RequestParam MultipartFile avatar) throws IOException {
        return employeeService.createAvatar(id, avatar.getInputStream(),
                avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.')));
    }

    @GetMapping("quit")
    public Object quit(@RequestParam Long id) throws IOException {
        employeeService.quit(id);
        return new RestResponse().add("ret", true).get();
    }
}

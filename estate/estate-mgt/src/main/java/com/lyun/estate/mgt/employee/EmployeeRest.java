package com.lyun.estate.mgt.employee;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public PageList<Employee> query(@RequestParam(required = false) Long departmentId,
                                    @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return employeeService.listByCompanyIdDepartmentId(mgtContext.getOperator().getCompanyId(),
                departmentId,
                pageBounds);
    }

    @PostMapping("edit")
    public Object edit(Employee entity) {
        return employeeService.update(entity);
    }


    @GetMapping("quit")
    public Object quit(@RequestParam Long id) throws IOException {
        employeeService.quit(id);
        return new RestResponse().add("ret", true).get();
    }
}

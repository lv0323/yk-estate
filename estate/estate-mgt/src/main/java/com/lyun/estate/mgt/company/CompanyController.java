package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.CompanyService;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.mgt.company.entity.CreateCompanyEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public CompanyController(CompanyService companyService, EmployeeService employeeService, DepartmentService departmentService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Transactional
    public Object create(CreateCompanyEntity entity) {
        return companyService.create(entity.getCompany());
    }
}

package com.lyun.estate.biz.company.service;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyEntity;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.def.Status;
import com.lyun.estate.biz.employee.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceFacade {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public CompanyServiceFacade(CompanyService companyService, EmployeeService employeeService, DepartmentService departmentService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Transactional
    public Company createCompany(CreateCompanyEntity entity) {
        companyService.create(entity.getCompany());

        Department department = new Department()
                .setCompanyId(entity.getCompany().getId())
                .setName(entity.getCompany().getName())
                .setShortName(entity.getCompany().getShortName())
                .setAddress(entity.getCompany().getAddress());
        departmentService.create(department);

        employeeService.createBoss(entity.getBoss()
                .setCompanyId(entity.getCompany().getId())
                .setDepartmentId(department.getId())
                .setStatus(Status.WORKING));
        return entity.getCompany();
    }
}

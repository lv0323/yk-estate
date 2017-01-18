package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.CompanyService;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.def.Status;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.mgt.company.entity.CreateCompanyEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("create")
    public Object create(CreateCompanyEntity entity) {
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

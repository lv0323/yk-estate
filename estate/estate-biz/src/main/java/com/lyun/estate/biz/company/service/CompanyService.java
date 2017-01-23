package com.lyun.estate.biz.company.service;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyEntity;
import com.lyun.estate.biz.company.repo.CompanyRepository;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.def.Status;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.core.utils.Validations;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {

    private final CompanyRepository repository;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public CompanyService(CompanyRepository repository, EmployeeService employeeService, DepartmentService departmentService) {
        this.repository = repository;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public Company create(Company company) {
        repository.insert(Validations.doValidate(company).setSecretKey(String.valueOf(new Date().getTime())));
        return company;
    }

    public Boolean lock(Long id, Boolean locked) {
        repository.lock(id, locked);
        return true;
    }

    public Boolean renew(Long id, java.sql.Date endDate) {
        repository.renew(id, endDate);
        return true;
    }

    public Company update(Company company) {
        Objects.requireNonNull(Validations.doValidate(company).getId());
        repository.update(company);
        return repository.selectOne(company.getId());
    }

    public List<Company> find(RowBounds rowBounds) {
        return repository.select(rowBounds);
    }

    @Transactional
    public Company createCompany(CreateCompanyEntity entity) {
        create(entity.getCompany());

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

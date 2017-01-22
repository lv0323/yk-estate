package com.lyun.estate.biz.company.entity;

import com.lyun.estate.biz.employee.entity.Employee;

public class CreateCompanyEntity {

    private Company company;
    private Employee boss;

    public CreateCompanyEntity() {
        this.company = new Company();
        this.boss = new Employee();
    }

    public CreateCompanyEntity(Company company, Employee boss) {
        this.company = company;
        this.boss = boss;
    }

    public Company getCompany() {
        return company;
    }

    public CreateCompanyEntity setCompany(Company company) {
        this.company = company;
        return this;
    }

    public Employee getBoss() {
        return boss;
    }

    public CreateCompanyEntity setBoss(Employee boss) {
        this.boss = boss;
        return this;
    }
}

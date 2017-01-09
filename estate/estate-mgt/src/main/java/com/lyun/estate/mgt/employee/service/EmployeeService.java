package com.lyun.estate.mgt.employee.service;

import com.lyun.estate.mgt.employee.entity.Employee;
import com.lyun.estate.mgt.employee.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    private final EmployeeRepo repo;

    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    public Employee create(Employee employee) {
        repo.insert(Objects.requireNonNull(employee));
        return employee;
    }

    public List<Employee> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(companyId);
    }

    public Employee update(Employee employee) {
        repo.update(Objects.requireNonNull(employee));
        return repo.selectById(employee.getId());
    }

    public Boolean deleteById(Long id) {
        return repo.deleteById(id) == 1;
    }
}

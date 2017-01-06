package com.lyun.estate.mgt.department.service;

import com.lyun.estate.mgt.department.entity.Department;
import com.lyun.estate.mgt.department.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentService {

    private final DepartmentRepo repo;

    public DepartmentService(DepartmentRepo repo) {
        this.repo = repo;
    }

    public Department create(Department department) {
        repo.insert(Objects.requireNonNull(department));
        return department;
    }

    public Boolean deleteById(Long id) {
        return repo.deleteById(Objects.requireNonNull(id)) == 1;
    }

    public Department update(Department department) {
        repo.update(Objects.requireNonNull(department));
        return department;
    }

    public List<Department> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }
}

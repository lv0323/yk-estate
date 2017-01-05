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

    public int insert(Department department) {
        return repo.insert(Objects.requireNonNull(department));
    }

    public int deleteById(Long id) {
        return repo.deleteById(Objects.requireNonNull(id));
    }

    public int update(Department department) {
        return repo.update(Objects.requireNonNull(department));
    }

    public List<Department> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }
}

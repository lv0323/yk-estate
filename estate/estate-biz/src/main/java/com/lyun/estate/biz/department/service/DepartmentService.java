package com.lyun.estate.biz.department.service;

import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.repo.DepartmentRepo;
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
        return repo.selectById(department.getId());
    }

    public Department selectById(Long id) {
        return repo.selectById(id);
    }

    public List<Department> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }
}

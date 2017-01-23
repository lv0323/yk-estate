package com.lyun.estate.biz.department.service;

import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.repo.DepartmentRepo;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        Objects.requireNonNull(department);
        if (Objects.equals(department.getParentId(), department.getId()) || refCheck(department))
            throw new EstateException(ExCode.INVALID_PARENT);
        repo.update(Objects.requireNonNull(department));
        return repo.selectById(department.getId());
    }

    private boolean refCheck(Department department) {
        List<Department> departmentList = selectByCompanyId(department.getCompanyId());
        Set<Long> childList = new HashSet<>();
        childList.add(department.getId());
        int lastSize = 0;
        while (lastSize != childList.size()) {
            lastSize = childList.size();
            for (Department depart : departmentList) {
                if (childList.contains(depart.getParentId()))
                    childList.add(depart.getId());
            }
        }
        return childList.contains(department.getParentId());
    }

    public Department selectById(Long id) {
        return repo.selectById(id);
    }

    public List<Department> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }
}

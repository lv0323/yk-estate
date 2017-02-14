package com.lyun.estate.biz.department.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.repo.DepartmentRepo;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class DepartmentService {

    private final DepartmentRepo repo;

    @Autowired
    private EmployeeService employeeService;

    public DepartmentService(DepartmentRepo repo) {
        this.repo = repo;
    }

    public Department create(Department department) {
        ExceptionUtil.checkNotNull("部门", department);
        ExceptionUtil.checkNotNull("公司编号", department.getCompanyId());
        ExceptionUtil.checkIllegal(Strings.isNullOrEmpty(department.getName()), "部门名称", department.getName());

        repo.insert(department);

        return department;
    }

    @Transactional
    public Boolean deleteById(Long id) {
        ExceptionUtil.checkNotNull("部门编号", id);
        Department department = repo.selectForUpdate(id);
        if (department == null) {
            return false;
        }
        if (department.getDeleted()) {
            return true;
        }
        Set<Long> childIds = findChildIds(department.getCompanyId(), department.getId());

        if (findChildIds(department.getCompanyId(), id).size() > 1) {
            throw new EstateException(ExCode.DEPT_HAS_CHILD, id);
        }
        if (!employeeService.listByDepartmentIds(Lists.newArrayList(childIds))
                .isEmpty()) {
            throw new EstateException(ExCode.DEPT_HAS_EMPLOYEE, id);
        }

        return repo.deleteById(id) == 1;
    }

    public Department updateInfo(Department department) {
        ExceptionUtil.checkNotNull("部门", department);
        ExceptionUtil.checkNotNull("部门编号", department.getId());
        repo.update(department);
        return repo.selectById(department.getId());
    }

    private Set<Long> findChildIds(Long companyId, Long departmentId) {
        List<Department> departmentList = selectByCompanyId(companyId);
        Set<Long> childIds = new HashSet<>();
        childIds.add(departmentId);
        int lastAdd;
        do {
            lastAdd = 0;
            for (Department department : departmentList) {
                if (childIds.contains(department.getParentId())
                        && !childIds.contains(department.getId())) {
                    childIds.add(department.getId());
                    lastAdd += 1;
                }
            }
        } while (lastAdd > 0);
        return childIds;
    }

    public List<Department> selectByCompanyId(Long companyId) {
        ExceptionUtil.checkNotNull("公司编号", companyId);
        return repo.selectByCompanyId(companyId);
    }

    public Department selectById(Long departmentId) {
        ExceptionUtil.checkNotNull("部门编号", departmentId);
        return repo.selectById(departmentId);
    }

    @Transactional
    public Department changeParent(Long id, Long parentId) {
        ExceptionUtil.checkNotNull("部门编号", id);
        ExceptionUtil.checkNotNull("父部门编号", parentId);
        Department dept = repo.selectForUpdate(id);
        Department parentDept = selectById(parentId);

        if (!Objects.equals(dept.getCompanyId(), parentDept.getCompanyId())) {
            throw new EstateException(ExCode.DEPT_ILLEGAL_PARENT);
        }

        if (findChildIds(dept.getCompanyId(), id).contains(parentId)) {
            throw new EstateException(ExCode.DEPT_INVALID_PARENT);
        }
        repo.updateParent(id, parentId);
        return repo.selectById(id);
    }
}

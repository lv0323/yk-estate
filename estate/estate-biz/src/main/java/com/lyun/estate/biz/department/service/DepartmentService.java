package com.lyun.estate.biz.department.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.department.repo.DepartmentRepo;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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
        if (Objects.equals(department.getParentId(), 0L)) {
            throw new EstateException(ExCode.DEPT_IS_PRIMARY, id);
        }

        Set<Long> childIds = findChildIds(department.getCompanyId(), department.getId());

        if (findChildIds(department.getCompanyId(), id).size() > 1) {
            throw new EstateException(ExCode.DEPT_HAS_CHILD, id);
        }
        if (!employeeService.listByCompanyIdDepartmentId(
                department.getCompanyId(), department.getId(), null)
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

    public Set<Long> findChildIds(Long companyId, Long departmentId) {
        List<Department> departmentList = repo.listAllByCompanyId(companyId);
        Set<Long> childIds = new HashSet<>();
        if (departmentList.stream().anyMatch(d->Objects.equals(d.getId(),departmentId))){
            childIds.add(departmentId);
        }
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

    private Map<Long, Integer> findDeptLevel(Long companyId) {
        HashMap<Long, Integer> deptLevel = new HashMap<>();
        List<Department> departmentList = repo.listAllByCompanyId(companyId);
        if (CollectionUtils.isEmpty(departmentList)) {
            return deptLevel;
        }
        Set<Long> depts = new HashSet<>();
        int level = 1;
        int lastAdd;
        Optional<Department> first = departmentList.stream()
                .filter(d -> Objects.equals(d.getParentId(), 0L))
                .findFirst();
        if (!first.isPresent()) {
            return deptLevel;
        } else {
            depts.add(first.get().getId());
            deptLevel.put(first.get().getId(), level);
            level++;
        }
        do {
            lastAdd = 0;
            for (Department department : departmentList) {
                if (depts.contains(department.getParentId()) && !depts.contains(department.getId())) {
                    depts.add(department.getId());
                    lastAdd += 1;
                    deptLevel.put(department.getId(), level);
                }
            }
            level++;
        } while (lastAdd > 0);

        return deptLevel;
    }

    public PageList<DepartmentDTO> selectByCompanyId(Long companyId, PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("公司编号", companyId);
        PageList<Department> departments = repo.selectByCompanyId(companyId, pageBounds);
        Map<Long, Integer> deptLevel = findDeptLevel(companyId);
        List<DepartmentDTO> deptDTOs = departments.stream().map(department -> {
            DepartmentDTO dto = new DepartmentDTO();
            BeanUtils.copyProperties(department, dto);
            dto.setPrimary(Objects.equals(dto.getParentId(), 0L));
            dto.setHasChild(findChildIds(department.getCompanyId(), department.getId()).size() > 1);
            dto.setLevel(deptLevel.get(department.getId()));
            return dto;
        }).collect(Collectors.toList());

        return new PageList<>(deptDTOs, departments.getPaginator());
    }

    public List<DepartmentDTO> listAllByCompanyId(Long companyId) {
        ExceptionUtil.checkNotNull("公司编号", companyId);
        List<Department> departments = repo.listAllByCompanyId(companyId);
        Map<Long, Integer> deptLevel = findDeptLevel(companyId);
        return departments.stream().map(department -> {
            DepartmentDTO dto = new DepartmentDTO();
            BeanUtils.copyProperties(department, dto);
            dto.setPrimary(Objects.equals(dto.getParentId(), 0L));
            dto.setHasChild(findChildIds(department.getCompanyId(), department.getId()).size() > 1);
            dto.setLevel(deptLevel.get(department.getId()));
            return dto;
        }).collect(Collectors.toList());
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
        if (Objects.equals(dept.getParentId(), 0L)) {
            throw new EstateException(ExCode.DEPT_IS_PRIMARY);
        }
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

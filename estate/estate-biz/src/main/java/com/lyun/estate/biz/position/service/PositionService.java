package com.lyun.estate.biz.position.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.employee.repo.EmployeeRepo;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.repo.PositionRepo;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepo repo;
    private final EmployeeRepo employeeRepo;

    public PositionService(PositionRepo repo, EmployeeRepo employeeRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
    }

    public Position create(Position position) {
        ExceptionUtil.checkNotNull("岗位", position);
        ExceptionUtil.checkNotNull("公司编号", position.getCompanyId());
        ExceptionUtil.checkNotNull("岗位性质", position.getType());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(position.getName()), "岗位名", position.getName());
        repo.insert(position);
        return position;
    }

    public Boolean deleteById(Long id) {
        ExceptionUtil.checkNotNull("岗位", id);
        if (employeeRepo.countByPositionId(id) > 0)
            throw new EstateException(ExCode.POSITION_HAS_EMPLOYEE);
        return repo.deleteById(id) == 1;
    }

    public Position update(Position position) {
        ExceptionUtil.checkNotNull("岗位", position);
        ExceptionUtil.checkNotNull("岗位性质", position.getType());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(position.getName()), "岗位名", position.getName());
        if (repo.update(position) > 0) {
            return repo.selectById(position.getId());
        }
        return null;
    }

    public List<Position> selectByCompanyId(Long companyId) {
        ExceptionUtil.checkNotNull("公司编号", companyId);
        return repo.selectByCompanyId(companyId);
    }

    public Position selectById(Long id) {
        return repo.selectById(id);
    }
}

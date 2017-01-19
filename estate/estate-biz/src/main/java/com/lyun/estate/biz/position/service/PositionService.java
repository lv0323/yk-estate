package com.lyun.estate.biz.position.service;

import com.lyun.estate.biz.employee.repo.EmployeeRepo;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.repo.PositionRepo;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PositionService {

    private final PositionRepo repo;
    private final EmployeeRepo employeeRepo;

    public PositionService(PositionRepo repo, EmployeeRepo employeeRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
    }

    public Position create(Position position) {
        repo.insert(Objects.requireNonNull(position));
        return position;
    }

    public Boolean deleteById(Long id) {
        Objects.requireNonNull(id);
        if (employeeRepo.countByPositionId(id) > 0)
            throw new EstateException(ExCode.HAS_EMPLOYEE);
        return repo.deleteById(id) == 1;
    }

    public Position update(Position position) {
        repo.update(Objects.requireNonNull(position));
        return repo.selectById(position.getId());
    }

    public List<Position> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }

    public Position selectById(Long id) {
        return repo.selectById(id);
    }
}

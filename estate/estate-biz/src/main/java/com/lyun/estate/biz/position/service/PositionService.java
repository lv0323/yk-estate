package com.lyun.estate.biz.position.service;

import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.repo.PositionRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PositionService {

    private final PositionRepo repo;

    public PositionService(PositionRepo repo) {
        this.repo = repo;
    }

    public Position create(Position position) {
        repo.insert(Objects.requireNonNull(position));
        return position;
    }

    public Boolean deleteById(Long id) {
        return repo.deleteById(Objects.requireNonNull(id)) == 1;
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

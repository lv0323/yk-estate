package com.lyun.estate.mgt.position.service;

import com.lyun.estate.mgt.position.entity.Position;
import com.lyun.estate.mgt.position.repo.PositionRepo;
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
        return position;
    }

    public List<Position> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }
}

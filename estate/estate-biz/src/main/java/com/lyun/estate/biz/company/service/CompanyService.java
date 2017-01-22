package com.lyun.estate.biz.company.service;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.repo.CompanyRepository;
import com.lyun.estate.core.utils.Validations;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company create(Company company) {
        repository.insert(Validations.doValidate(company).setSecretKey(String.valueOf(new Date().getTime())));
        return company;
    }

    public Boolean lock(Long id, Boolean locked) {
        repository.lock(id, locked);
        return true;
    }

    public Boolean renew(Long id, java.sql.Date endDate) {
        repository.renew(id, endDate);
        return true;
    }

    public Company update(Company company) {
        Objects.requireNonNull(Validations.doValidate(company).getId());
        repository.update(company);
        return repository.selectOne(company.getId());
    }

    public List<Company> find(RowBounds rowBounds) {
        return repository.select(rowBounds);
    }
}

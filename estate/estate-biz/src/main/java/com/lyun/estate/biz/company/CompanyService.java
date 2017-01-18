package com.lyun.estate.biz.company;

import com.lyun.estate.biz.company.repo.CompanyRepository;
import com.lyun.estate.core.utils.Validations;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}

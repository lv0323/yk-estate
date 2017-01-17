package com.lyun.estate.biz.company;

import com.lyun.estate.biz.company.repo.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }
}

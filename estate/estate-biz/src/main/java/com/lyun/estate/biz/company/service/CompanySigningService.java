package com.lyun.estate.biz.company.service;

import com.lyun.estate.biz.company.entity.CompanySigning;
import com.lyun.estate.biz.company.repo.CompanySigningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-05-15.
 */
@Service
public class CompanySigningService {

    @Autowired
    CompanySigningRepo companySigningRepo;

    CompanySigning create(CompanySigning companySigning) {
        companySigningRepo.create(companySigning);
        return companySigningRepo.findOne(companySigning.getId());
    }
}

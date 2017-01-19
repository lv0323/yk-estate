package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyEntity;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.biz.company.service.CompanyServiceFacade;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyServiceFacade companyServiceFacade;

    public CompanyController(CompanyService companyService, CompanyServiceFacade companyServiceFacade) {
        this.companyService = companyService;
        this.companyServiceFacade = companyServiceFacade;
    }

    @PostMapping("create")
    public Object create(CreateCompanyEntity entity) {
        return companyServiceFacade.createCompany(entity);
    }

    @GetMapping("lock")
    public Object lock(@RequestParam Long id, @RequestParam Boolean locked) {
        return new RestResponse().add("ret", companyService.lock(id, locked)).get();
    }

    @PostMapping("edit")
    public Object edit(Company company) {
        return companyService.update(company);
    }

    @GetMapping("query-all")
    public Object queryAll() {
        return companyService.findAll();
    }
}

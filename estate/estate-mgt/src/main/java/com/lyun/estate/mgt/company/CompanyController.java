package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyEntity;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.biz.company.service.CompanyServiceFacade;
import com.lyun.estate.mgt.supports.RestResponse;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

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

    @GetMapping("renew")
    public Object renew(@RequestParam Long id, @RequestParam Date endDate) {
        return new RestResponse().add("ret", companyService.renew(id, endDate)).get();
    }

    @PostMapping("edit")
    public Object edit(Company company) {
        return companyService.update(company);
    }

    @GetMapping("query")
    public Object queryAll(@RequestParam(required = false, defaultValue = "0") int offset, @RequestParam(required = false, defaultValue = "2147483647") int limit) {
        return companyService.find(new RowBounds(offset, limit));
    }
}

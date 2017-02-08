package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyEntity;
import com.lyun.estate.biz.company.service.CompanyService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("api/company")
public class CompanyRest {

    private final CompanyService service;

    public CompanyRest(CompanyService service) {
        this.service = service;
    }

    @PostMapping("create")
    public Object create(CreateCompanyEntity entity) {
        return service.createCompany(entity);
    }

    @GetMapping("lock")
    public Object lock(@RequestParam Long id, @RequestParam Boolean locked) {
        return new RestResponse().add("ret", service.lock(id, locked)).get();
    }

    @GetMapping("renew")
    public Object renew(@RequestParam Long id, @RequestParam Date endDate) {
        return new RestResponse().add("ret", service.renew(id, endDate)).get();
    }

    @PostMapping("edit")
    public Object edit(Company company) {
        return service.update(company);
    }

    @GetMapping("query")
    public Object queryAll(@RequestParam(required = false, defaultValue = "0") int offset,
                           @RequestParam(required = false, defaultValue = "2147483647") int limit) {
        return service.find(new RowBounds(offset, limit));
    }

    @GetMapping("/{companyId}")
    public Company findOne(@PathVariable Long companyId) {
        return service.findOne(companyId);
    }

}
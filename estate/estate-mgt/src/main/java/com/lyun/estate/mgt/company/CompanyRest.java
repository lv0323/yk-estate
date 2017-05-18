package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.Company;
import com.lyun.estate.biz.company.entity.CreateCompanyInfo;
import com.lyun.estate.mgt.company.service.CompanyMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("api/company")
public class CompanyRest {

    @Autowired
    private CompanyMgtService companyMgtService;

    @PostMapping(value = "create")
    public Company create(@RequestParam Long cityId,
                          @RequestParam Long parentId,
                          @RequestParam Long partAId,
                          @RequestParam CompanyDefine.Type type,
                          @RequestParam String name,
                          @RequestParam String abbr,
                          @RequestParam String address,
                          @RequestParam(required = false) String introduction,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                          @RequestParam Integer years,
                          @RequestParam Integer storeCount,
                          @RequestParam BigDecimal price,
                          @RequestParam String bossName,
                          @RequestParam String mobile) {

        CreateCompanyInfo info = new CreateCompanyInfo()
                .setCityId(cityId)
                .setParentId(parentId)
                .setPartAId(partAId)
                .setType(type)
                .setName(name)
                .setAbbr(abbr)
                .setAddress(address)
                .setIntroduction(introduction)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setYears(years)
                .setStoreCount(storeCount)
                .setPrice(price)
                .setBossName(bossName)
                .setMobile(mobile);

        return companyMgtService.createCompany(info);
    }

}

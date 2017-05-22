package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.domain.CreateCompanyInfo;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.mgt.company.service.CompanyMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @GetMapping("parents")
    List<Company> findParents() {
        return companyMgtService.getParents();
    }

    @GetMapping("departments")
    List<DepartmentDTO> findDepartments(@RequestParam Long companyId) {
        return companyMgtService.findDepartment(companyId);
    }

    @GetMapping("partAIds")
    List<Employee> findPartAIds(@RequestParam Long companyId, Long departmentId) {
        return companyMgtService.findPartAIds(companyId, departmentId);
    }

}

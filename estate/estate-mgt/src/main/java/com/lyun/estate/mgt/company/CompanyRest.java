package com.lyun.estate.mgt.company;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.CompanyDTO;
import com.lyun.estate.biz.company.domain.CompanySigningDTO;
import com.lyun.estate.biz.company.domain.CreateCompanyInfo;
import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.entity.CompanySigning;
import com.lyun.estate.biz.department.entity.DepartmentDTO;
import com.lyun.estate.biz.employee.domain.EmployeeDTO;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.mgt.company.service.CompanyMgtService;
import com.lyun.estate.mgt.supports.CommonResp;
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
    List<EmployeeDTO> findPartAIds(@RequestParam Long companyId, Long departmentId) {
        return companyMgtService.findPartAIds(companyId, departmentId);
    }

    @GetMapping("list")
    PageList<CompanyDTO> list(@RequestParam(required = false) Long cityId,
                              @RequestParam(required = false) Long parentId,
                              @RequestParam(required = false) CompanyDefine.Type companyType,
                              @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return companyMgtService.list(cityId, parentId, companyType, pageBounds);
    }

    @GetMapping("list-signing")
    PageList<CompanySigningDTO> listSigning(@RequestParam(required = false) Long companyId,
                                            @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return companyMgtService.lisSigningByCompanyId(companyId, pageBounds);
    }

    @PostMapping("update-info")
    Company updateInfo(@RequestParam Long companyId,
                       @RequestParam String name,
                       @RequestParam String abbr,
                       @RequestParam String address,
                       @RequestParam(required = false) String introduction,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        Company company = new Company().setId(companyId)
                .setName(name)
                .setAbbr(abbr)
                .setAddress(address)
                .setIntroduction(introduction)
                .setStartDate(startDate)
                .setEndDate(endDate);
        return companyMgtService.updateInfo(company);
    }

    @PostMapping("update-boss")
    Company updateBoss(@RequestParam Long companyId,
                       @RequestParam Long bossId) {
        return companyMgtService.updateBoss(companyId, bossId);
    }

    @PostMapping("renew-signing")
    CompanySigning createSigning(@RequestParam Long companyId,
                                 @RequestParam Long partAId,
                                 @RequestParam Integer years,
                                 @RequestParam Integer storeCount,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                 @RequestParam BigDecimal price) {
        CompanySigning signing = new CompanySigning()
                .setCompanyId(companyId)
                .setPartAId(partAId)
                .setYears(years)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setStoreCount(storeCount)
                .setPrice(price);
        return companyMgtService.renewSigning(signing);
    }

    @PostMapping("update-signing")
    CompanySigning updateSigning(@RequestParam Long signingId,
                                 @RequestParam Integer years,
                                 @RequestParam Integer storeCount,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                 @RequestParam BigDecimal price) {
        CompanySigning signing = new CompanySigning()
                .setId(signingId)
                .setYears(years)
                .setStoreCount(storeCount)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setPrice(price);
        return companyMgtService.updateSigningInfo(signing);
    }

    @PostMapping("delete-signing")
    CommonResp deleteSigning(@RequestParam Long signingId) {
        return companyMgtService.deleteSigning(signingId) ? CommonResp.succeed() : CommonResp.failed();
    }

}

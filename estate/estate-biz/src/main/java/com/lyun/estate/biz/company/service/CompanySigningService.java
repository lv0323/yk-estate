package com.lyun.estate.biz.company.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.company.domain.CompanySigningDTO;
import com.lyun.estate.biz.company.entity.CompanySigning;
import com.lyun.estate.biz.company.repo.CompanySigningRepo;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-05-15.
 */
@Service
public class CompanySigningService {

    @Autowired
    private CompanySigningRepo companySigningRepo;

    @Autowired
    private EmployeeService employeeService;


    public CompanySigning create(CompanySigning companySigning) {
        companySigningRepo.create(companySigning);
        return companySigningRepo.findOne(companySigning.getId());
    }

    public PageList<CompanySigningDTO> listByCompanyId(Long companyId, PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("公司编号", companyId);
        PageList<CompanySigningDTO> result = companySigningRepo.list(companyId, pageBounds);
        result.forEach(
                t -> t.setPartA(employeeService.selectDTOById(t.getPartAId()))
        );
        return result;
    }

    public CompanySigningDTO getLastSigning(Long companyId) {
        CompanySigningDTO result = companySigningRepo.findLastSigning(companyId);
        if (result != null) {
            result.setPartA(employeeService.selectDTOById(result.getPartAId()));
        }
        return result;
    }
}

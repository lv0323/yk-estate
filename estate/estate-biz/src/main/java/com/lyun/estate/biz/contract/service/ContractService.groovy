package com.lyun.estate.biz.contract.service

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.google.common.collect.Lists
import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.contract.entity.Contract
import com.lyun.estate.biz.contract.entity.ContractDTO
import com.lyun.estate.biz.contract.entity.ContractFilter
import com.lyun.estate.biz.contract.entity.ContractSelector
import com.lyun.estate.biz.contract.repo.ContractRepo
import com.lyun.estate.biz.customer.service.CustomerService
import com.lyun.estate.biz.department.entity.Department
import com.lyun.estate.biz.department.service.DepartmentService
import com.lyun.estate.biz.employee.service.EmployeeService
import com.lyun.estate.biz.fang.service.FangProcessService
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.core.supports.exceptions.ExceptionUtil
import com.lyun.estate.core.utils.ValidateUtil
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import static java.util.Objects.nonNull

/**
 * Created by Jeffrey on 2017-03-09.
 */
@Service
class ContractService {
    @Autowired
    ContractRepo contractRepo

    @Autowired
    DepartmentService departmentService

    @Autowired
    EmployeeService employeeService

    @Autowired
    CustomerService customerService

    @Autowired
    MgtFangService mgtFangService

    @Autowired
    FangProcessService fangProcessService

    @Transactional
    Contract create(Contract contract) {
        ExceptionUtil.checkIllegal(
                ValidateUtil.isMobile(contract.getAssignorMobile()), "房东手机", contract.getAssignorMobile())
        ExceptionUtil.checkIllegal(
                ValidateUtil.isIdNo(contract.getAssignorIdNo()), "房东身份证", contract.getAssignorIdNo())
        ExceptionUtil.checkIllegal(
                ValidateUtil.isMobile(contract.getAssigneeMobile()), "客户手机", contract.getAssigneeMobile())
        ExceptionUtil.checkIllegal(
                ValidateUtil.isIdNo(contract.getAssigneeIdNo()), "客户身份证", contract.getAssigneeIdNo())
        contract.setProcess(ContractDefine.Process.CREATED)
        
        if (contractRepo.save(contract) > 0) {
            if (contract.type == ContractDefine.Type.DEAL) {
                fangProcessService.deal(contract.fangId)
            }
            return contractRepo.findOne(contract.getId())
        }
        throw new EstateException(ExCode.CREATE_FAIL, "", contract.toString())
    }

    Contract close(long contractId, ContractDefine.Process process) {
        ExceptionUtil.checkIllegal(process != null && process.isEnd(), "状态", process)

        if (contractRepo.close(contractId, process) > 0) {
            return contractRepo.findOne(contractId)
        } else {
            Contract contract = contractRepo.findOne(contractId)
            if (nonNull(contract) && contract.getProcess().isEnd()) {
                return contract
            }
        }
        throw new EstateException(
                ExCode.UPDATE_FAIL,
                "合同",
                new Contract().setId(contractId).setProcess(process).toString())
    }

    PageList<ContractDTO> list(ContractFilter filter, PageBounds pageBounds) {
        ContractSelector selector = new ContractSelector()

        BeanUtils.copyProperties(filter, selector, 'class', 'metaClass')

        //department and children
        if (nonNull(filter.getDepartmentId())) {
            if (Objects.equals(true, filter.getChildren())) {
                Department department = departmentService.selectById(filter.getDepartmentId())
                if (nonNull(department)) {
                    Set<Long> childIds = departmentService.findChildIds(department.getCompanyId(),
                            filter.getDepartmentId())
                    selector.setDepartmentIds(Lists.newArrayList(childIds))
                }
            } else {
                selector.setDepartmentIds(Lists.newArrayList(filter.getDepartmentId()))
            }
        }

        PageList<ContractDTO> result = contractRepo.list(selector, pageBounds)
        result.forEach({
            it.setAvatarURI(employeeService.getAvatarURI(it.getEmployeeId()))
            it.setFangTiny(mgtFangService.getFangTiny(it.getFangId()))
        })
        return result
    }
}

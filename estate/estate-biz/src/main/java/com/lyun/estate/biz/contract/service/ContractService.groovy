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
import com.lyun.estate.biz.employee.entity.Employee
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

    Contract create(Contract contract) {
        ExceptionUtil.checkNotNull("房源编号", contract.fangId)
        ExceptionUtil.checkIllegal(
                ValidateUtil.isMobile(contract.getAssignorMobile()), "房东手机", contract.getAssignorMobile())
        ExceptionUtil.checkIllegal(
                ValidateUtil.isIdNo(contract.getAssignorIdNo()), "房东身份证", contract.getAssignorIdNo())
        ExceptionUtil.checkIllegal(
                ValidateUtil.isMobile(contract.getAssigneeMobile()), "客户手机", contract.getAssigneeMobile())
        ExceptionUtil.checkIllegal(
                ValidateUtil.isIdNo(contract.getAssigneeIdNo()), "客户身份证", contract.getAssigneeIdNo())

        List<Contract> contractList = contractRepo.findByFangId(contract.fangId)

        if (contractList.stream().anyMatch({ it.getProcess() == ContractDefine.Process.CREATED })) {
            throw new EstateException(ExCode.CONTRACT_FOR_FANG_EXIST)
        }

        if (contractList.stream().anyMatch({ it.getProcess() == ContractDefine.Process.SUCCESS })) {
            throw new EstateException(ExCode.CONTRACT_FOR_FANG_SUCCEED)
        }

        contract.setProcess(ContractDefine.Process.CREATED)

        Employee employee = employeeService.selectById(contract.getEmployeeId())

        contract.setCompanyId(employee.companyId)
        contract.setDepartmentId(employee.departmentId)

        if (contractRepo.save(contract) > 0) {
            return contractRepo.findOne(contract.getId())
        }
        throw new EstateException(ExCode.CREATE_FAIL, "", contract.toString())
    }

    @Transactional
    Contract close(long contractId, ContractDefine.Process process) {
        ExceptionUtil.checkIllegal(process != null && process.isEnd(), "状态", process)

        Boolean result

        Contract contract = contractRepo.findOne(contractId)
        if (Objects.equals(process, contract.getProcess())) {
            result = true
        } else {
            result = contractRepo.close(contractId, process) > 0
        }

        if (result && process == ContractDefine.Process.SUCCESS
                && contract.type == ContractDefine.Type.DEAL) {
            fangProcessService.deal(contract.fangId)
        }

        if (result) {
            return contractRepo.findOne(contractId)
        } else {
            throw new EstateException(
                    ExCode.UPDATE_FAIL,
                    "合同",
                    new Contract().setId(contractId).setProcess(process).toString())
        }
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

        return listBySelector(selector, pageBounds)
    }

    private PageList<ContractDTO> listBySelector(ContractSelector selector, PageBounds pageBounds) {
        PageList<ContractDTO> result = contractRepo.list(selector, pageBounds)
        result.forEach({
            it.setAvatarURI(employeeService.getAvatarURI(it.getEmployeeId()))
            it.setFangTiny(mgtFangService.getFangTiny(it.getFangId()))
        })
        return result
    }

    List<Contract> findByFangId(long fangId) {
        contractRepo.findByFangId(fangId)
    }


}

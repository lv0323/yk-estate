package com.lyun.estate.mgt.contract.service

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.contract.entity.Contract
import com.lyun.estate.biz.contract.entity.ContractDTO
import com.lyun.estate.biz.contract.entity.ContractFilter
import com.lyun.estate.biz.contract.service.ContractService
import com.lyun.estate.biz.customer.service.CustomerService
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.mgt.context.MgtContext
import com.lyun.estate.mgt.context.Operator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Jeffrey on 2017-03-09.
 */
@Service
class ContractMgtService {
    @Autowired
    ContractService contractService

    @Autowired
    CustomerService customerService

    @Autowired
    MgtContext mgtContext

    Contract create(Contract contract) {
        Operator operator = mgtContext.getOperator()
        contractService.create(
                contract.setCompanyId(operator.companyId).
                        setDepartmentId(operator.departmentId).
                        setEmployeeId(operator.id)
        )
    }

    Contract close(long contractId, ContractDefine.Process process) {
        contractService.close(contractId, process)
    }

    PageList<ContractDTO> list(ContractFilter filter, PageBounds pageBounds) {
        filter.setCompanyId(mgtContext.operator.companyId)
        contractService.list(filter, pageBounds)
    }

    ContractDTO findOne(long id) {
        ContractFilter filter = new ContractFilter().setId(id)
        contractService.list(filter, null).
                stream().
                findAny().
                orElseThrow({ new EstateException(ExCode.NOT_FOUND, id, "合同") })
    }
}

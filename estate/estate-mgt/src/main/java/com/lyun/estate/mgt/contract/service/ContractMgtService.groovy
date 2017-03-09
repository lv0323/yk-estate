package com.lyun.estate.mgt.contract.service

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.contract.entity.Contract
import com.lyun.estate.biz.contract.entity.ContractDTO
import com.lyun.estate.biz.contract.entity.ContractFilter
import com.lyun.estate.biz.contract.service.ContractService
import com.lyun.estate.biz.customer.def.CustomerDefine
import com.lyun.estate.biz.customer.entity.Customer
import com.lyun.estate.biz.customer.service.CustomerService
import com.lyun.estate.mgt.context.MgtContext
import com.lyun.estate.mgt.context.Operator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    Contract create(Long fangId, ContractDefine.Type contractType, String customerName,
                    CustomerDefine.Source customerSource, String customerMobile) {
        Operator operator = mgtContext.getOperator()
        Customer customer = customerService.createSimple(new Customer().setName(customerName)
                .setSource(customerSource).setMobile(customerMobile).setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId()).setEmployeeId(operator.getId()))

        contractService.create(
                new Contract().setFangId(fangId).
                        setCustomerId(customer.getId()).
                        setCompanyId(operator.getCompanyId()).
                        setDepartmentId(operator.getDepartmentId()).
                        setEmployeeId(operator.getId())
                        .setType(contractType)
        )
    }

    Contract close(long contractId, ContractDefine.Process process) {
        contractService.close(contractId, process)
    }

    PageList<ContractDTO> list(ContractFilter filter, PageBounds pageBounds) {
        filter.setCompanyId(mgtContext.operator.companyId)
        contractService.list(filter, pageBounds)
    }
}

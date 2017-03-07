package com.lyun.estate.mgt.showing.service

import com.lyun.estate.biz.customer.def.CustomerDefine
import com.lyun.estate.biz.customer.entity.Customer
import com.lyun.estate.biz.customer.service.CustomerService
import com.lyun.estate.biz.showing.def.ShowingDefine
import com.lyun.estate.biz.showing.entity.Showing
import com.lyun.estate.biz.showing.service.ShowingService
import com.lyun.estate.mgt.context.MgtContext
import com.lyun.estate.mgt.context.Operator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Jeffrey on 2017-03-07.
 */
@Service
class ShowingMgtService {

    @Autowired
    CustomerService customerService

    @Autowired
    ShowingService showingService

    @Autowired
    MgtContext mgtContext


    @Transactional
    Showing create(Long fangId, String customerName,
                   CustomerDefine.Source customerSource, String customerMobile) {
        Operator operator = mgtContext.getOperator()
        Customer customer = customerService.createSimple(new Customer().setName(customerName)
                .setSource(customerSource).setMobile(customerMobile).setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId()).setEmployeeId(operator.getId()))

        return showingService.create(
                new Showing().setFangId(fangId).setProcess(ShowingDefine.Process.CREATED)
                        .setCustomerId(customer.getId()).setCompanyId(operator.getCompanyId())
                        .setDepartmentId(operator.getDepartmentId()).setEmployeeId(operator.getId()))
    }
}


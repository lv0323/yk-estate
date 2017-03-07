package com.lyun.estate.biz.customer.service

import com.google.common.base.Strings
import com.lyun.estate.biz.customer.entity.Customer
import com.lyun.estate.biz.customer.entity.CustomerTiny
import com.lyun.estate.biz.customer.repo.CustomerRepo
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.core.supports.exceptions.ExceptionUtil
import com.lyun.estate.core.utils.ValidateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Jeffrey on 2017-03-07.
 */
@Service
class CustomerService {

    @Autowired
    CustomerRepo customerRepo

    Customer createSimple(Customer customer) {
        ExceptionUtil.checkNotNull("客户信息", customer)
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(customer.getName()), "客户名", customer.getName())
        ExceptionUtil.checkIllegal(Objects.nonNull(customer.getSource()), "客户来源", customer.getSource())
        ExceptionUtil.checkIllegal(ValidateUtil.isMobile(customer.getMobile()), "手机", customer.getMobile())

        Customer existed = customerRepo.findCustomerByCompanyIdAndMobile(customer.getCompanyId(), customer.getMobile())
        if (Objects.nonNull(existed)) {
            return existed
        } else {
            if (customerRepo.save(customer) > 0) {
                return customerRepo.findOne(customer.getId())
            }
            throw new EstateException(ExCode.CREATE_FAIL, "客户", customer.toString())
        }

    }

    CustomerTiny getTiny(Long customerId) {
        ExceptionUtil.checkNotNull("客户编号", customerId)
        customerRepo.getTiny(customerId)
    }
}

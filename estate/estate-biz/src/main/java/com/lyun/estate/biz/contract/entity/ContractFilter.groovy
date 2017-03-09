package com.lyun.estate.biz.contract.entity

import com.lyun.estate.biz.contract.def.ContractDefine
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by Jeffrey on 2017-03-09.
 */
@Builder(builderStrategy = SimpleStrategy)
class ContractFilter {
    Long fangId
    Date minCreateTime
    Date maxCreateTime
    ContractDefine.Process process
    Long companyId
    Long departmentId
    Boolean children
    Long employeeId
}

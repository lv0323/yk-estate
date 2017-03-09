package com.lyun.estate.biz.contract.entity

import com.lyun.estate.biz.contract.def.ContractDefine
import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by Jeffrey on 2017-03-09.
 */
@Builder(builderStrategy = SimpleStrategy)
@ToString(includeNames = true, ignoreNulls = true)
class ContractSelector {
    Long fangId
    Date minCreateTime
    Date maxCreateTime
    ContractDefine.Process process
    ContractDefine.Type type
    Long companyId
    List<Long> departmentIds
    Long employeeId
}

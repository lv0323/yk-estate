package com.lyun.estate.biz.contract.entity

import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.fang.def.HouseType
import com.lyun.estate.biz.support.def.BizType
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
    BizType bizType
    HouseType houseType
    Long companyId
    Long departmentId
    Boolean children
    Long employeeId
}

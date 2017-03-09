package com.lyun.estate.biz.contract.entity

import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.customer.entity.CustomerTiny
import com.lyun.estate.biz.fang.domian.MgtFangTiny
import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by Jeffrey on 2017-03-09.
 */
@Builder(builderStrategy = SimpleStrategy)
@ToString(includeNames = true, ignoreNulls = true)
class ContractDTO {
    Long id
    Long customerId
    Long fangId
    Long companyId
    Long departmentId
    String departmentName
    String employeeName
    String avatarURI
    Long employeeId
    ContractDefine.Type type
    Date createTime
    Date updateTime
    ContractDefine.Process process
    Boolean isDeleted
    MgtFangTiny fangTiny
    CustomerTiny customerTiny
}

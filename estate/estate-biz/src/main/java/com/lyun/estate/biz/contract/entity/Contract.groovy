package com.lyun.estate.biz.contract.entity

import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.fang.def.HouseType
import com.lyun.estate.biz.fang.def.PriceUnit
import com.lyun.estate.biz.support.def.BizType
import com.lyun.estate.biz.support.def.IdentitySource
import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by Jeffrey on 2017-03-09.
 */
@Builder(builderStrategy = SimpleStrategy)
@ToString(includeNames = true, ignoreNulls = true)
class Contract {
    Long id
    Long fangId
    Long companyId
    Long departmentId
    Long employeeId
    Long operatorId
    ContractDefine.Type type
    ContractDefine.Process process
    HouseType houseType
    String certifAddress
    String certifNo
    BigDecimal estateArea
    BigDecimal price
    PriceUnit priceUnit
    BizType bizType
    String assignorName
    String assignorMobile
    IdentitySource assignorIdSource
    String assignorIdNo
    String assigneeName
    String assigneeMobile
    IdentitySource assigneeIdSource
    String assigneeIdNo
    String note
    Date createTime
    Date closeTime
    Date updateTime
    Boolean isDeleted

}

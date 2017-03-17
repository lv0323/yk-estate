package com.lyun.estate.biz.event.entity

import com.lyun.estate.biz.event.def.EventDefine
import com.lyun.estate.biz.support.def.DomainType
import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by Jeffrey on 2017-03-14.
 */
@Builder(builderStrategy = SimpleStrategy)
@ToString(includeNames = true, ignoreNulls = true)
class Event {
    long id
    String uuid
    EventDefine.Type type
    Long domainId
    DomainType domainType
    String content
    Date createTime
    Date updateTime
    Boolean isDeleted
}

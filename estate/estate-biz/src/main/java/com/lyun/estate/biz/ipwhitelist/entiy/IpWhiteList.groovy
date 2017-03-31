package com.lyun.estate.biz.ipwhitelist.entiy

import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by Jeffrey on 2017-03-31.
 */
@Builder(builderStrategy = SimpleStrategy)
@ToString(includeNames = true, ignoreNulls = true)
class IpWhiteList {
    long id
    Long companyId
    String ip
    boolean isDeleted
    Date createTime
    Date updateTime;
}

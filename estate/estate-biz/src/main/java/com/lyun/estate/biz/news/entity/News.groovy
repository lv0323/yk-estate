package com.lyun.estate.biz.news.entity

import com.lyun.estate.biz.news.define.NewsDefine
import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

/**
 * Created by Jeffrey on 2017-03-28.
 */
@Builder(builderStrategy = SimpleStrategy)
@ToString(includeNames = true, ignoreNulls = true)
class News {
    long id
    String title
    String summary
    NewsDefine.Category category
    NewsDefine.Scope scope
    Long imageId
    String newsUrl
    Boolean isDeleted
    Date createTime
    Date updateTime
}

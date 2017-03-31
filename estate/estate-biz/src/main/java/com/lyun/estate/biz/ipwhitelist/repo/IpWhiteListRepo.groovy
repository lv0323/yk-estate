package com.lyun.estate.biz.ipwhitelist.repo

import com.lyun.estate.core.config.EstateCacheConfig
import org.apache.ibatis.annotations.Select
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository

/**
 * Created by Jeffrey on 2017-03-31.
 */
@Repository
@CacheConfig(cacheManager = EstateCacheConfig.MANAGER_10_5K)
interface IpWhiteListRepo {
    @Select("SELECT ip FROM t_ip_white_list WHERE company_id = #{CompanyId} AND is_deleted =FALSE ;")
//    @Cacheable(cacheNames = "ipWhiteList")
    List<String> ipWhiteList(long CompanyId)
}

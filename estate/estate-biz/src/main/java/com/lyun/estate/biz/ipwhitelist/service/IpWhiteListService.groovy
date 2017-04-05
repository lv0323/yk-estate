package com.lyun.estate.biz.ipwhitelist.service

import com.lyun.estate.biz.ipwhitelist.repo.IpWhiteListRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Jeffrey on 2017-03-30.
 */
@Service
class IpWhiteListService {

    @Autowired
    IpWhiteListRepo ipWhiteListRepo

    List<String> ipWhiteList(long companyId) {
        ipWhiteListRepo.ipWhiteList(companyId)
    }
}

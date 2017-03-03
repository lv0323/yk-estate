package com.lyun.estate.biz.message.service;

import com.lyun.estate.biz.support.def.DomainType;
import org.springframework.stereotype.Service;

/**
 * Created by jesse on 2017/3/3.
 */
@Service
public class AdminUserUtils {

    public Long getSenderIdByDomainType(DomainType domainType) {
        switch (domainType) {
            case FANG:
                return 1L;
            case XIAO_QU:
                return 2L;
            case REPORT:
                return 3L;
            case NOTICE:
                return 4L;
            default:
                return null;
        }
    }
}

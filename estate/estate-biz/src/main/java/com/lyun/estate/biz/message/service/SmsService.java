package com.lyun.estate.biz.message.service;

import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.core.config.CacheConfig;
import com.lyun.estate.core.supports.YN;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.resources.SmsCode;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    Environment environment;
    @Autowired
    @Qualifier("evictCacheManager")
    CacheManager cacheManager;

    public SmsResponse sendMessage(String smsId, String mobile) {
        if (!ValidateUtil.isMobile(mobile)) {
            throw new ValidateException("mobile.isNull", "手机号码不能为空");
        }
        if (!ValidateUtil.isMobile(mobile)) {
            throw new ValidateException("mobile.illegal", "手机号码非法");
        }
        String smsCode = "100000";
        String serial = "01";
        if (YN.Y == YN.valueOf(environment.getProperty("message.sms.send.enable"))) {

        }
        String smsKv = smsId + ":" + mobile + ":" + smsCode + ":" + serial;
        cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).put(smsKv, smsKv);
        return new SmsResponse().setMobile(mobile).setSmsId(smsId).setSerial(serial);
    }

    public boolean isSmsCodeCorrect(SmsCode smsCode) {
        String smsKv = smsCode.getSmsId() + ":" + smsCode.getMobile() + ":" + smsCode.getCode() + ":" + smsCode.getSerial();
        boolean result = smsKv.equals(cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).get(smsKv));
        cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).evict(smsKv);
        return result;
    }
}

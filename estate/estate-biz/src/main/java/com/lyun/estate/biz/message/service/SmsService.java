package com.lyun.estate.biz.message.service;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.message.resources.SmsResource;
import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.biz.message.service.validator.SmsResourceValidator;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.core.config.CacheConfig;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.types.YN;
import com.lyun.estate.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

@Service
public class SmsService {

    @Autowired
    Environment environment;
    @Autowired
    @Qualifier("evictCacheManager")
    CacheManager cacheManager;
    @Autowired
    UserMapper userMapper;

    private boolean isDefaultSend() {
        return YN.Y == YN.valueOf(environment.getProperty("message.sms.send.enable"));
    }

    public SmsResponse sendCheckSms(SmsResource smsResource) {
        DataBinder dataBinder = new DataBinder(smsResource, "sms");
        dataBinder.setValidator(new SmsResourceValidator(userMapper));
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.sms.send", "短信发送失败", bindingResult.getAllErrors());
        }
        String smsCode = "100000";
        String serial = "01";
        if (isDefaultSend()) {
//TODO sms implements
            serial = CommonUtil.randomNumberSeq(2);
        }
        String smsId = CommonUtil.getUuid();
        String smsKv = smsId + ":" + smsResource.getMobile() + ":" + smsCode + ":" + serial + ":" + smsResource.getType();
        cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).put(smsKv, smsKv);
        return new SmsResponse().setMobile(smsResource.getMobile()).setSmsId(smsId).setSerial(serial);
    }

    public boolean isSmsCodeCorrect(SmsCode smsCode) {
        String smsKv = smsCode.getSmsId() + ":" + smsCode.getMobile() + ":" + smsCode.getCode() + ":" + smsCode.getSerial() + ":" + smsCode.getType();
        boolean result = smsKv.equals(cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).get(smsKv));
        cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).evict(smsKv);
        return result;
    }
}

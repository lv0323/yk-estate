package com.lyun.estate.biz.message.service;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.message.resources.SmsResource;
import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.biz.message.service.validator.SmsResourceValidator;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.core.config.CacheConfig;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.types.YN;
import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    @Autowired
    ExecutionContext executionContext;

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
        String smsKv = smsId + ":" + smsResource.getMobile() + ":" + smsCode + ":" + serial + ":" + smsResource.getType() + ":" + executionContext.getClientId();
        cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).put(smsKv, smsKv);
        return new SmsResponse().setMobile(smsResource.getMobile()).setSmsId(smsId).setSerial(serial);
    }

    public boolean isSmsCodeCorrect(SmsCode smsCode) {
        if (StringUtils.isEmpty(smsCode.getMobile())) {
            throw new ValidateException("X-SMS-CODE.mobile.isNull", "手机号码为空");
        }
        if (StringUtils.isEmpty(smsCode.getId())) {
            throw new ValidateException("X-SMS-CODE.id.isNull", "短信id为空");
        }
        if (StringUtils.isEmpty(smsCode.getType())) {
            throw new ValidateException("X-SMS-CODE.type.isNull", "短信类型为空");
        }
        if (StringUtils.isEmpty(smsCode.getSerial())) {
            throw new ValidateException("X-SMS-CODE.serial.isNull", "短信序列号为空");
        }
        if (StringUtils.isEmpty(smsCode.getCode())) {
            throw new ValidateException("X-SMS-CODE.code.isNull", "短信验证码为空");
        }
        if (!ValidateUtil.isClientId(smsCode.getClientId())) {
            throw new ValidateException("X-SMS-CODE.clientId.isNull", "客户端编号不存在");
        }
        String smsKv = smsCode.getId() + ":" + smsCode.getMobile() + ":" + smsCode.getCode() + ":" + smsCode.getSerial() + ":" + smsCode.getType() + ":" + smsCode.getClientId();
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).get(smsKv);
        boolean result = valueWrapper != null && smsKv.equals(valueWrapper.get());
        cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME).evict(smsKv);
        return result;
    }
}

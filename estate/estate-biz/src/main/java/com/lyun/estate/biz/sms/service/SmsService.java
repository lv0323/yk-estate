package com.lyun.estate.biz.sms.service;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.sms.resources.SmsResource;
import com.lyun.estate.biz.sms.resources.SmsResponse;
import com.lyun.estate.biz.sms.service.validator.SmsResourceValidator;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.core.config.EstateCacheConfig;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.types.YN;
import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import com.lyun.sms.client.SmsClient;
import com.lyun.sms.client.exceptions.SmsClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
    @Qualifier(EstateCacheConfig.MANAGER_10_5K)
    CacheManager cacheManager;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RestContext restContext;
    @Autowired
    SmsClient smsClient;
    @Value("${service.sms.check.code.template.di.chan.id}")
    Long diChanId;
    @Value("${service.sms.check.code.template.yin.jia.id}")
    Long yinJiaId;
    @Autowired
    private EmployeeService employeeService;

    private boolean isRandomSend() {
        return YN.Y == YN.valueOf(environment.getProperty("message.sms.code.random.enable"));
    }

    public SmsResponse sendCheckSms(SmsResource smsResource, boolean... fromMgt) {
        DataBinder dataBinder = new DataBinder(smsResource, "sms");
        dataBinder.setValidator(new SmsResourceValidator(userMapper, employeeService));
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.sms.send", "短信发送失败", bindingResult.getAllErrors());
        }
        String smsId = CommonUtil.getUuid();
        String smsCode = "100000";
        String serial = "01";
        if (isRandomSend()) {
            serial = CommonUtil.randomNumberSeq(2);
            smsCode = CommonUtil.randomNumberSeq(6);
        }
        try {
            long templateId = (fromMgt.length > 0 && fromMgt[0]) ? diChanId : yinJiaId;
            if (!smsId.equals(smsClient.sendSms(smsId, smsResource.getMobile(), templateId, smsCode, serial))) {
                throw new ValidateException("error.sms.send", "短信发送失败");
            }
        } catch (SmsClientException e) {
            throw new ValidateException(e.getCode(), e.getMessage(), e);
        }
        String smsKv = smsId + ":" + smsResource.getMobile() + ":" + smsCode + ":" + serial + ":" + smsResource.getType() + ":" + restContext
                .getClientId();
        cacheManager.getCache(EstateCacheConfig.SMS_CACHE).put(smsKv, smsKv);
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
        String smsKv = smsCode.getId() + ":" + smsCode.getMobile() + ":" + smsCode.getCode() + ":" + smsCode.getSerial() + ":" + smsCode
                .getType() + ":" + smsCode.getClientId();
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(EstateCacheConfig.SMS_CACHE).get(smsKv);
        boolean result = valueWrapper != null && smsKv.equals(valueWrapper.get());
        cacheManager.getCache(EstateCacheConfig.SMS_CACHE).evict(smsKv);
        return result;
    }

}

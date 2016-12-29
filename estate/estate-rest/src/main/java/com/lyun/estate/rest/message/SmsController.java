package com.lyun.estate.rest.message;

import com.lyun.estate.biz.auth.captcha.Captcha;
import com.lyun.estate.biz.auth.captcha.CaptchaArgumentResolver;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.biz.auth.sms.CheckSmsCode;
import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.sms.SmsCodeArgumentResolver;
import com.lyun.estate.biz.message.resources.SmsResource;
import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sms")
@RestController
public class SmsController {
    @Autowired
    SmsService smsService;

    @CheckCaptcha
    @PostMapping
    public SmsResponse sendMessage(SmsResource smsResource,
                                   @RequestHeader(value = CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        return smsService.sendCheckSms(smsResource);
    }

    @CheckSmsCode
    @PostMapping("/correct")
    public boolean isCorrect(@RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        return true;
    }
}

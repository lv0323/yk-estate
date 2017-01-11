package com.lyun.estate.rest.message;

import com.lyun.estate.biz.auth.captcha.Captcha;
import com.lyun.estate.biz.auth.captcha.CaptchaArgumentResolver;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.biz.message.resources.SmsResource;
import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.types.SmsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public SmsResponse sendMessage(@RequestParam String mobile,
                                   @RequestParam SmsType type,
                                   @RequestHeader(value = CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        return smsService.sendCheckSms(new SmsResource().setMobile(mobile).setType(type));
    }

    @GetMapping(value = "test")
    public SmsResponse send() {
        return smsService.sendCheckSms(new SmsResource().setMobile("15021916760").setType(SmsType.LOGIN));
    }

}

package com.lyun.estate.rest.message;

import com.lyun.estate.biz.auth.captcha.Captcha;
import com.lyun.estate.biz.auth.captcha.CaptchaArgumentResolver;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.biz.message.resources.SmsResource;
import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.biz.message.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sms")
@RestController
public class SmsController {
    @Autowired
    SmsService smsService;

    @CheckCaptcha
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public SmsResponse sendMessage(@RequestBody SmsResource smsResource,
                                   @RequestHeader(value = CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        return smsService.sendCheckSms(smsResource);
    }

}

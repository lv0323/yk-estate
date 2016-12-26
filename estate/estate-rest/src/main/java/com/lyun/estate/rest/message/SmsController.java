package com.lyun.estate.rest.message;

import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.annotations.CheckSmsCode;
import com.lyun.estate.core.supports.annotations.CheckVerifyCode;
import com.lyun.estate.core.supports.resolvers.SmsCodeArgumentResolver;
import com.lyun.estate.core.supports.resolvers.VerifyCodeArgumentResolver;
import com.lyun.estate.core.supports.resources.SmsCode;
import com.lyun.estate.core.supports.resources.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/sms")
@RestController
public class SmsController {
    @Autowired
    SmsService smsService;

    @CheckVerifyCode
    @PostMapping
    public SmsResponse sendMessage(@RequestParam(value = "mobile") String mobile,
                                   @RequestHeader(value = VerifyCodeArgumentResolver.VERIFY_CODE_HEADER) VerifyCode verifyCode) {
        String smsId = UUID.randomUUID().toString();
        return smsService.sendMessage(smsId, mobile);
    }

    @CheckSmsCode
    @PostMapping("/correct")
    public boolean isCorrect(@RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        return true;
    }
}

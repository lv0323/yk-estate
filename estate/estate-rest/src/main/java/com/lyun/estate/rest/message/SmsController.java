package com.lyun.estate.rest.message;

import com.lyun.estate.biz.message.resources.SmsResponse;
import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.annotations.CheckVerifyCode;
import com.lyun.estate.core.supports.resources.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/sms")
@RestController
public class SmsController {
    @Autowired
    SmsService smsService;

    @CheckVerifyCode
    @PostMapping
    public SmsResponse sendMessage(@RequestParam(value = "mobile") String mobile,
                                   @RequestHeader(value = "X-VERIFY-CODE") VerifyCode verifyCode) {
        String smsId = UUID.randomUUID().toString();
        return smsService.sendMessage(smsId, mobile);
    }
}

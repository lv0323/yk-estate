package com.lyun.estate.rest.user;


import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.core.supports.resolvers.SmsCodeArgumentResolver;
import com.lyun.estate.core.supports.resources.SmsCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping
    public void register(RegisterResource registerResource,
                         @RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {

    }

}

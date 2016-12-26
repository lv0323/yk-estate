package com.lyun.estate.rest.user;


import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.biz.user.resources.RegisterResponse;
import com.lyun.estate.biz.user.resources.TokenResponse;
import com.lyun.estate.biz.user.service.UserService;
import com.lyun.estate.core.supports.annotations.CheckVerifyCode;
import com.lyun.estate.core.supports.resolvers.SmsCodeArgumentResolver;
import com.lyun.estate.core.supports.resolvers.VerifyCodeArgumentResolver;
import com.lyun.estate.core.supports.resources.SmsCode;
import com.lyun.estate.core.supports.resources.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public RegisterResponse register(RegisterResource registerResource,
                                     @RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        return userService.register(registerResource);
    }


    @PostMapping("/login")
    @CheckVerifyCode
    public TokenResponse login(LoginResource loginResource,
                               @RequestHeader(VerifyCodeArgumentResolver.VERIFY_CODE_HEADER) VerifyCode verifyCode) {
        return userService.login(loginResource);
    }

}

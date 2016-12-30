package com.lyun.estate.rest.user;


import com.lyun.estate.biz.auth.captcha.Captcha;
import com.lyun.estate.biz.auth.captcha.CaptchaArgumentResolver;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.biz.auth.sms.CheckSmsCode;
import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.sms.SmsCodeArgumentResolver;
import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.biz.user.resources.RegisterResponse;
import com.lyun.estate.biz.user.resources.TokenResponse;
import com.lyun.estate.biz.user.service.UserService;
import com.lyun.estate.core.supports.types.SmsType;
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
    @CheckSmsCode
    public RegisterResponse register(RegisterResource registerResource,
                                     @RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        smsCode.setType(SmsType.REGISTER);
        return userService.register(registerResource, smsCode);
    }


    @PostMapping("/login")
    @CheckCaptcha
    public TokenResponse login(LoginResource loginResource,
                               @RequestHeader(CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        return userService.login(loginResource, null);
    }

    @PostMapping("/sms-login")
    @CheckSmsCode
    public TokenResponse loginBySmsCode(@RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        smsCode.setType(SmsType.LOGIN);
        return userService.login(null, smsCode);
    }

    @PostMapping("change-password")
    @CheckToken
    public TokenResponse changePassword(ChangePasswordResource changePasswordResource,
                                        @RequestHeader("auth") JWTToken token) {
        return userService.changePassword(changePasswordResource, null, token);
    }

    @PostMapping("forget-password")
    @CheckSmsCode
    public TokenResponse forgetPassword(ChangePasswordResource changePasswordResource,
                                        @RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        smsCode.setType(SmsType.FORGET_PASSWORD);
        return userService.changePassword(changePasswordResource, smsCode, null);
    }

    @PostMapping("/is-login")
    @CheckToken
    public boolean isLogin(@RequestHeader("auth") JWTToken token) {
        return true;
    }
}

package com.lyun.estate.rest.user;


import com.lyun.estate.biz.auth.captcha.Captcha;
import com.lyun.estate.biz.auth.captcha.CaptchaArgumentResolver;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.biz.auth.sms.CheckSmsCode;
import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.sms.SmsCodeArgumentResolver;
import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToeknArgumentResolver;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.sms.def.SmsType;
import com.lyun.estate.biz.user.resources.*;
import com.lyun.estate.biz.user.service.UserService;
import com.lyun.estate.rest.supports.resources.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckSmsCode
    public RegisterResponse register(@RequestParam(required = false) String password,
                                     @RequestParam(required = false) String salt,
                                     @RequestParam(required = false) String hash,
                                     @RequestParam(required = false) boolean login,
                                     @RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        smsCode.setType(SmsType.REGISTER);
        return userService.register(new RegisterResource().setPassword(password)
                .setHash(hash).setSalt(salt).setLogin(login), smsCode);
    }

    @PostMapping(value = "salt", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public SaltResponse salt(@RequestParam(required = false) String mobile,
                             @RequestParam(required = false) String userName,
                             @RequestParam(required = false) String email) {
        return userService.getUserSalt(new SaltResource().setMobile(mobile).setUserName(userName).setEmail(email));
    }

    @PostMapping(value = "/login", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckCaptcha
    public TokenResponse login(@RequestParam(required = false) String userName,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) String mobile,
                               @RequestParam(required = false) String password,
                               @RequestParam(required = false) String signature,
                               @RequestHeader(CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        return userService.login(new LoginResource().setUserName(userName).setEmail(email)
                .setMobile(mobile).setPassword(password).setSignature(signature), null);
    }

    @PostMapping("/sms-login")
    @CheckSmsCode
    public TokenResponse loginBySmsCode(@RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        smsCode.setType(SmsType.LOGIN);
        return userService.login(null, smsCode);
    }

    @PostMapping(value = "change-password", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public TokenResponse changePassword(@RequestParam(required = false) String oldPassword,
                                        @RequestParam(required = false) String signature,
                                        @RequestParam(required = false) String password,
                                        @RequestParam(required = false) String salt,
                                        @RequestParam(required = false) String hash,
                                        @RequestParam(required = false) boolean login,
                                        @RequestHeader(JWTToeknArgumentResolver.AUTH_HEADER) JWTToken token) {
        return userService.changePassword(new ChangePasswordResource().setOldPassword(oldPassword)
                        .setSignature(signature).setPassword(password).setSalt(salt).setHash(hash).setLogin(login),
                null,
                token);
    }

    @PostMapping(value = "forget-password", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckSmsCode
    public TokenResponse forgetPassword(@RequestParam(required = false) String signature,
                                        @RequestParam(required = false) String password,
                                        @RequestParam(required = false) String salt,
                                        @RequestParam(required = false) String hash,
                                        @RequestParam(required = false) boolean login,
                                        @RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode) {
        smsCode.setType(SmsType.FORGET_PASSWORD);
        return userService.changePassword(new ChangePasswordResource().setSignature(signature)
                .setPassword(password).setSalt(salt).setHash(hash).setLogin(login), smsCode, null);
    }

    @PostMapping("/is-login")
    @CheckToken
    public CommonResponse isLogin(@RequestHeader(JWTToeknArgumentResolver.AUTH_HEADER) JWTToken token) {
        return new CommonResponse().setSuccess(true);
    }


    @GetMapping("refresh-token")
    public TokenResponse refreshToken(@RequestHeader(JWTToeknArgumentResolver.AUTH_HEADER) String refreshToken) {
        return userService.refreshToken(refreshToken);
    }
}

package com.lyun.estate.mgt.auth;

import com.google.common.base.Strings;
import com.lyun.estate.biz.auth.captcha.Captcha;
import com.lyun.estate.biz.auth.captcha.CaptchaArgumentResolver;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.biz.auth.sms.CheckSmsCode;
import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.sms.SmsCodeArgumentResolver;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.mgt.auth.def.SaltSugar;
import com.lyun.estate.mgt.auth.service.AuthMgtService;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import com.lyun.estate.mgt.supports.Constant;
import com.lyun.estate.mgt.supports.RestResponse;
import eu.bitwalker.useragentutils.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthRest {

    private final AuthMgtService authMgtService;

    @Autowired
    private MgtContext mgtContext;

    public AuthRest(AuthMgtService authMgtService) {
        this.authMgtService = authMgtService;
    }

    @PostMapping("active")
    @CheckSmsCode
    public Object active(@RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode,
                         @RequestParam String password,
                         @RequestParam String secretKey) {
        return new RestResponse().add("ret", authMgtService.active(smsCode.getMobile(), password, secretKey)).get();
    }

    @GetMapping("salt")
    public Object salt(@RequestParam String mobile) {
        SaltSugar saltSugar = authMgtService.saltSugar(mobile);
        return new RestResponse().add("ret", true)
                .add("salt", saltSugar.getSalt())
                .add("sugar", saltSugar.getSugar())
                .get();
    }

    @CheckCaptcha
    @GetMapping("login")
    public Object login(@RequestParam String mobile,
                        @RequestParam String password,
                        HttpSession session,
                        @RequestHeader(CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        Employee employee = authMgtService.login(mobile, password);
        // 自动绑定设备功能
        // deviceId为空且mgtContext中设备为mobile,则自动绑定
        String deviceId = null;
        if (Strings.isNullOrEmpty(employee.getDeviceId()) && mgtContext.getDeviceType() == DeviceType.MOBILE) {
            deviceId = authMgtService.bindDeviceId(employee.getId());
            employee.setDeviceId(deviceId);
        }

        session.setAttribute(Constant.SESSION_IS_LOGIN, true);
        session.setAttribute(Constant.SESSION_OPERATOR, new Operator()
                .setId(employee.getId())
                .setCompanyId(employee.getCompanyId())
                .setCompanyIpCheck(employee.getCompanyIpCheck())
                .setSysAdmin(employee.getSysAdmin())
                .setDeviceId(employee.getDeviceId())
                .setCityId(employee.getCityId())
                .setDepartmentId(employee.getDepartmentId())
                .setPositionId(employee.getPositionId())
                .setBoss(employee.getBoss())
                .setAgent(employee.getAgent())
                .setGender(employee.getGender())
                .setName(employee.getName())
                .setMobile(employee.getMobile())
                .setPositionName(employee.getPositionName())
                .setDepartmentName(employee.getDepartmentName()));
        session.setMaxInactiveInterval(3600 * 4);
        return new RestResponse().add("ret", true).add("user", employee).add("deviceId", deviceId).get();
    }

    @GetMapping("logout")
    public Object logout(HttpSession session) {
        Boolean result = authMgtService.logout();
        session.removeAttribute(Constant.SESSION_IS_LOGIN);
        session.invalidate();
        return new RestResponse().add("ret", result).get();
    }
}

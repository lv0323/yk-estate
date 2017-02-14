package com.lyun.estate.mgt.auth;

import com.lyun.estate.biz.auth.captcha.Captcha;
import com.lyun.estate.biz.auth.captcha.CaptchaArgumentResolver;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.biz.auth.sms.CheckSmsCode;
import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.sms.SmsCodeArgumentResolver;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.context.Operator;
import com.lyun.estate.mgt.supports.Constant;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class AuthRest {

    private final EmployeeService employeeService;

    @SuppressWarnings("unchecked")
    public AuthRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("active")
    @CheckSmsCode
    public Object active(@RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode,
                         @RequestParam String password,
                         @RequestParam String secretKey) {
        return new RestResponse().add("ret", employeeService.active(smsCode.getMobile(), password, secretKey)).get();
    }

    @GetMapping("salt")
    public Object salt(@RequestParam String mobile) {
        Employee employee = employeeService.selectByMobile(mobile);
        if (employee == null)
            throw new EstateException(ExCode.EMPLOYEE_NO_SALT);
        return new RestResponse().add("ret", true)
                .add("salt", employee.getSalt())
                .add("sugar", employeeService.sugar(mobile))
                .get();
    }

    @CheckCaptcha
    @GetMapping("login")
    public Object login(@RequestParam String mobile,
                        @RequestParam String password,
                        HttpSession session,
                        @RequestHeader(CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        Employee employee = employeeService.login(mobile, password);
        session.setAttribute(Constant.SESSION_IS_LOGIN, true);
        session.setAttribute(Constant.SESSION_OPERATOR, new Operator()
                .setId(employee.getId())
                .setCompanyId(employee.getCompanyId())
                .setDepartmentId(employee.getDepartmentId())
                .setPositionId(employee.getPositionId())
                .setBoss(employee.getIsBoss())
                .setAgent(employee.getIsAgent())
                .setGender(employee.getGender())
                .setName(employee.getName())
                .setMobile(employee.getMobile()));
        return new RestResponse().add("ret", true).add("user", employee).get();
    }

    @GetMapping("logout")
    public Object logout(HttpSession session) {
        session.removeAttribute(Constant.SESSION_IS_LOGIN);
        session.invalidate();
        return new RestResponse().add("ret", true).get();
    }
}

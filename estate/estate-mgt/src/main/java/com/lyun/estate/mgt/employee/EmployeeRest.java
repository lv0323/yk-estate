package com.lyun.estate.mgt.employee;

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
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("api/employee")
public class EmployeeRest {

    private final EmployeeService employeeService;
    private final SessionRepository<Session> sessionRepository;

    @SuppressWarnings("unchecked")
    public EmployeeRest(EmployeeService employeeService, SessionRepository sessionRepository) {
        this.employeeService = employeeService;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("create")
    public Object create(Employee entity, @SessionAttribute LoginEmployee employee) {
        Objects.requireNonNull(entity).setCompanyId(employee.getCompanyId());
        return employeeService.create(entity);
    }

    @GetMapping("query")
    public Object query(@SessionAttribute LoginEmployee employee) {
        return employeeService.selectByCompanyId(employee.getCompanyId());
    }

    @PostMapping("edit")
    public Object edit(Employee entity, @SessionAttribute LoginEmployee employee) {
        return employeeService.update(entity);
    }

    @GetMapping("avatar")
    public Object avatar(@RequestParam Long id, @SessionAttribute LoginEmployee employee) {
        return new RestResponse().add("url", employeeService.getAvatar(id));
    }

    @PostMapping("avatar")
    public Object avatar(@RequestParam Long id, @RequestParam MultipartFile avatar, @SessionAttribute LoginEmployee employee) throws IOException {
        return employeeService.createAvatar(id, avatar.getInputStream(),
                avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.')));
    }

    @GetMapping("quit")
    public Object quit(@RequestParam Long id, @SessionAttribute LoginEmployee employee) throws IOException {
        employeeService.quit(id);
        return new RestResponse().add("ret", true).get();
    }

    @PostMapping("active")
    @CheckSmsCode
    public Object active(@RequestHeader(SmsCodeArgumentResolver.SMS_CODE_HEADER) SmsCode smsCode, @RequestParam String password, @RequestParam String secretKey) {
        return new RestResponse().add("ret", employeeService.active(smsCode.getMobile(), password, secretKey)).get();
    }

    @GetMapping("salt")
    public Object salt(@RequestParam String mobile) {
        Employee employee = employeeService.selectByMobile(mobile);
        if (employee == null)
            throw new EstateException(ExCode.CANT_SALT);
        return new RestResponse().add("ret", true).add("salt", employee.getSalt()).add("sugar", employeeService.sugar(mobile)).get();
    }

    @CheckCaptcha
    @GetMapping("login")
    public Object login(@RequestParam String mobile, @RequestParam String password, HttpSession cookieSession,
                        @RequestHeader(CaptchaArgumentResolver.CAPTCHA_HEADER) Captcha captcha) {
        Employee employee = employeeService.login(mobile, password);
        cookieSession.setAttribute("employee", true);
        Session session = sessionRepository.createSession();
        session.setAttribute("cookieSession", cookieSession.getId());
        session.setAttribute("employee", new LoginEmployee(employee.getId(), employee.getCompanyId(),
                employee.getDepartmentId(), employee.getPositionId(), employee.getIsBoss(), employee.getIsAgent(),
                employee.getMobile(), employee.getName(), employee.getGender()));
        sessionRepository.save(session);
        return new RestResponse().add("ret", true).add("token", session.getId()).add("user", employee).get();
    }

    @GetMapping("logout")
    public Object logout(HttpSession session, @SessionAttribute LoginEmployee employee) {
        Session cookieSession = sessionRepository.getSession((String) session.getAttribute("cookieSession"));
        cookieSession.removeAttribute("employee");
        sessionRepository.save(cookieSession);
        session.invalidate();
        return new RestResponse().add("ret", true).get();
    }
}

package com.lyun.estate.mgt.employee;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final SmsService smsService;

    public EmployeeController(EmployeeService employeeService, SmsService smsService) {
        this.employeeService = employeeService;
        this.smsService = smsService;
    }

    @PostMapping("create")
    public Object create(Employee entity, @SessionAttribute Employee employee) {
        Objects.requireNonNull(entity).setCompanyId(employee.getCompanyId());
        return employeeService.create(entity);
    }

    @GetMapping("query")
    public Object query(@SessionAttribute Employee employee) {
        return employeeService.selectByCompanyId(employee.getCompanyId());
    }

    @PostMapping("edit")
    public Object edit(Employee entity, @SessionAttribute Employee employee) {
        return employeeService.update(entity);
    }

    @PostMapping("active")
    public Object active(@RequestHeader SmsCode smsCode, @RequestParam String password, @RequestParam String secretKey) {
        if (!smsService.isSmsCodeCorrect(smsCode))
            throw new ValidateException("SmsCode", "SMS Code错误");
        return new RestResponse().add("ret", employeeService.active(smsCode.getMobile(), password, secretKey)).get();
    }

    @GetMapping("salt")
    public Object salt(@RequestParam String mobile) {
        Employee employee = employeeService.selectByMobile(mobile);
        if (employee == null)
            return new RestResponse().add("ret", false).get();
        return new RestResponse().add("ret", true).add("salt", employee.getSalt()).add("sugar", employeeService.salt(mobile)).get();
    }

    @GetMapping("login")
    public Object login(@RequestParam String mobile, @RequestParam String password, HttpSession session) {
        Employee employee = employeeService.login(mobile, password);
        if (employee == null)
            return new RestResponse().add("ret", false).get();
        session.setAttribute("employee", employee);
        return new RestResponse().add("ret", true).add("token", session.getId()).get();
    }

    @GetMapping("logout")
    public Object logout(HttpSession session, @SessionAttribute Employee employee) {
        session.invalidate();
        return new RestResponse().add("ret", true).get();
    }
}

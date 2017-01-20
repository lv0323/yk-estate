package com.lyun.estate.mgt.employee;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public Object avatar(@RequestParam Long id, @RequestParam MultipartFile avatar, @SessionAttribute LoginEmployee employee) throws IOException {
        return employeeService.avatar(id, avatar.getInputStream(),
                avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.')));
    }

    @GetMapping("quit")
    public Object quit(@RequestParam Long id, @SessionAttribute LoginEmployee employee) throws IOException {
        employeeService.quit(id);
        return new RestResponse().add("ret", true).get();
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
        return new RestResponse().add("ret", true).add("salt", employee.getSalt()).add("sugar", employeeService.sugar(mobile)).get();
    }

    @GetMapping("login")
    public Object login(@RequestParam String mobile, @RequestParam String password, HttpSession session) {
        Employee employee = employeeService.login(mobile, password);
        if (employee == null)
            return new RestResponse().add("ret", false).get();
        session.setAttribute("employee", new LoginEmployee(employee.getId(), employee.getCompanyId(),
                employee.getDepartmentId(), employee.getPositionId(), employee.getIsBoss(), employee.getIsAgent(),
                employee.getMobile(), employee.getName(), employee.getGender()));
        return new RestResponse().add("ret", true).add("token", session.getId()).get();
    }

    @GetMapping("logout")
    public Object logout(HttpSession session, @SessionAttribute LoginEmployee employee) {
        session.invalidate();
        return new RestResponse().add("ret", true).get();
    }
}

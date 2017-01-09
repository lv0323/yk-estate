package com.lyun.estate.mgt.employee.controller;

import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.mgt.employee.entity.ActiveEntity;
import com.lyun.estate.mgt.employee.entity.Employee;
import com.lyun.estate.mgt.employee.service.EmployeeService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public Object create(@RequestBody @Valid Employee employee, BindingResult result) {
        if (result.hasErrors())
            throw new ValidateException("参数不合法", result.getAllErrors());
        return employeeService.create(employee);
    }

    @GetMapping("query")
    public Object query(@RequestParam Long companyId) {
        return employeeService.selectByCompanyId(companyId);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        return new RestResponse().add("ret", employeeService.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(@RequestBody @Valid Employee employee, BindingResult result) {
        if (result.hasErrors())
            throw new ValidateException("参数不合法", result.getAllErrors());
        return employeeService.update(employee);
    }

    @PostMapping("active")
    public Object active(@RequestBody ActiveEntity entity) {
        if (!smsService.isSmsCodeCorrect(entity.getSmsCode()))
            throw new ValidateException("SmsCode", "SMS Code错误");
        return new RestResponse().add("ret", employeeService.active(entity.getSmsCode().getMobile(), entity.getPassword())).get();
    }

    @GetMapping("salt")
    public Object salt(@RequestParam String mobile) {
        Employee employee = employeeService.selectByMobile(mobile);
        if (employee == null)
            return new RestResponse().add("ret", false).get();
        return new RestResponse().add("ret", true).add("salt", employee.getSalt()).add("r", employeeService.salt(mobile)).get();
    }

    @GetMapping("login")
    public Object login(@RequestParam String mobile, @RequestParam String password, HttpSession session) {
        Employee employee = employeeService.login(mobile, password);
        if (employee == null)
            return new RestResponse().add("ret", false).get();
        session.setAttribute("companyId", employee.getCompanyId());
        return new RestResponse().add("ret", true).add("token", session.getId()).get();
    }

    @GetMapping("company-id")
    public Object companyId(@RequestHeader("x-auth-token") String token, HttpSession session) {
        return session.getAttribute("companyId");
    }
}

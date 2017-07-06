package com.lyun.estate.biz.sms.service.validator;

import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.sms.def.SmsType;
import com.lyun.estate.biz.sms.resources.SmsResource;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

public class SmsResourceValidator implements Validator {
    private UserMapper userMapper;
    private EmployeeService employeeService;

    public SmsResourceValidator(UserMapper userMapper, EmployeeService employeeService) {
        this.userMapper = userMapper;
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SmsResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SmsResource smsResource = (SmsResource) target;
        if (StringUtils.isEmpty(smsResource.getMobile())) {
            errors.reject("mobile.isNull", "手机号码不能为空");
        }
        if (!ValidateUtil.isMobile(smsResource.getMobile())) {
            errors.reject("mobile.illegal", "手机号码不正确");
        }
        if (StringUtils.isEmpty(smsResource.getType())) {
            errors.reject("sms.type.isNull", "短信类型不正确");
        }
        if (errors.hasErrors()) {
            return;
        }
        if (smsResource.getType() == SmsType.LOGIN || smsResource.getType() == SmsType.FORGET_PASSWORD) {
            if (userMapper.findUserByMobile(smsResource.getMobile()) == null) {
                errors.reject("mobile.not.register", new String[]{smsResource.getMobile()}, "该手机号未注册");
            }
        } else if (smsResource.getType() == SmsType.REGISTER) {
            if (userMapper.findUserByMobile(smsResource.getMobile()) != null) {
                errors.reject("mobile.register", new String[]{smsResource.getMobile()}, "该手机号已注册");
            }
        } else if (smsResource.getType() == SmsType.EMPLOYEE_ACTIVE) {
            Employee employee = employeeService.selectByMobile(smsResource.getMobile());
            if (employee == null) {
                throw new EstateException(ExCode.NOT_FOUND, "手机号", "员工");
            }
            if (Objects.nonNull(employee.getSalt())) {
                throw new EstateException(ExCode.EMPLOYEE_ACTIVE);
            }
        }
    }
}

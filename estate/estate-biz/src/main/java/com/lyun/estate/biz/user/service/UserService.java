package com.lyun.estate.biz.user.service;

import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.biz.user.service.validator.RegisterResourceValidator;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    void register(RegisterResource registerResource) {
        DataBinder dataBinder = new DataBinder(registerResource, "userRegister");
        dataBinder.setValidator(new RegisterResourceValidator(userMapper));
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.user.register", bindingResult.getAllErrors());
        }


    }

}

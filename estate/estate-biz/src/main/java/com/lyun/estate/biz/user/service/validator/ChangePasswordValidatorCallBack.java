package com.lyun.estate.biz.user.service.validator;

import com.lyun.estate.biz.user.domain.User;

public interface ChangePasswordValidatorCallBack {
    void callBack(User user);
}

package com.lyun.estate.biz.auth.captcha;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CheckCaptcha {
    boolean evictAfterSuccess() default true;
}

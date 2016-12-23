package com.lyun.estate.rest.test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.auth.CheckToken;
import com.lyun.estate.biz.auth.JWTToken;
import com.lyun.estate.biz.auth.TokenProvider;
import com.lyun.estate.core.exception.EstateException;
import com.lyun.estate.core.exception.ExCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping(value = "/string")
    public String string() {
        return "string";
    }

    @GetMapping(value = "/error")
    public String error() {
        throw new EstateException(ExCode.PARAM_ILLEGAL, "用户名", "1234");
    }

    @GetMapping(value = "/paginator")
    public PageBounds page(PageBounds pageBounds) {
        return pageBounds;
    }

    @GetMapping(value = "/token")
    public JWTToken token() {
        String token = tokenProvider.generate("timbo");
        return new JWTToken(token);
    }

    @PostMapping(value = "/validate")
    @CheckToken
    public String validate(@RequestHeader("auth") JWTToken token) {
        return tokenProvider.getUsername(token.getToken());
    }

}

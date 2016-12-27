package com.lyun.estate.rest.user;

import cn.apiclub.captcha.servlet.CaptchaServletUtil;
import com.lyun.estate.biz.user.service.CaptchaService;
import com.lyun.estate.biz.auth.captcha.CheckCaptcha;
import com.lyun.estate.core.supports.resolvers.VerifyCodeArgumentResolver;
import com.lyun.estate.core.supports.resources.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    CaptchaService captchaService;

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(@RequestParam("clientId") long clientId,
                         @RequestParam("verifyId") String verifyId,
                         @RequestParam("width") int width,
                         @RequestParam("height") int height,
                         HttpServletResponse response) {
        BufferedImage image = captchaService.getVerifyCodeImage(clientId, verifyId, width, height);
        CaptchaServletUtil.writeImage(response, image);
    }


    @PostMapping(path = "/correct", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CheckCaptcha
    @ResponseBody
    public boolean correct(@RequestHeader(VerifyCodeArgumentResolver.VERIFY_CODE_HEADER) VerifyCode verifyCode) {
        return true;
    }

}

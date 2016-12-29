package com.lyun.estate.mgt.auth;


import cn.apiclub.captcha.servlet.CaptchaServletUtil;
import com.lyun.estate.biz.user.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    CaptchaService captchaService;

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(@RequestParam("clientId") int clientId,
                         @RequestParam("id") String id,
                         @RequestParam("width") int width,
                         @RequestParam("height") int height,
                         HttpServletResponse response) {
        BufferedImage image = captchaService.getCaptcha(clientId, id, width, height);
        CaptchaServletUtil.writeImage(response, image);
    }

}

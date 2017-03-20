package com.lyun.estate.mgt.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by robin on 17/3/20.
 */
@Controller
@RequestMapping("/auth")
public class AuthPage {

    @GetMapping("/activation")
    public ModelAndView survey() {
        HashMap<String, Object> params = new HashMap<>();
        return new ModelAndView("/auth/activation", params);
    }
}

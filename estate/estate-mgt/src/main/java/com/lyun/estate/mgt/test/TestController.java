package com.lyun.estate.mgt.test;

import com.lyun.estate.biz.clock.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;

@Controller
public class TestController {

    @Autowired
    ClockService clockService;
    @GetMapping("/index")
    public ModelAndView index() {
        HashMap<String, String> params = new HashMap<>();
        params.put("message", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(clockService.currentTime()));
        return new ModelAndView("index", params);
    }

}

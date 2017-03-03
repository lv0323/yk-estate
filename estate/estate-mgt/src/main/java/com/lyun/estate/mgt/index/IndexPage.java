package com.lyun.estate.mgt.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class IndexPage {

    @GetMapping("/index")
    public ModelAndView index(HttpServletResponse response) {
        response.setHeader("X-PAGE-FLAG", "/index");
        HashMap<String, String> params = new HashMap<>();
        return new ModelAndView("index", params);
    }

}

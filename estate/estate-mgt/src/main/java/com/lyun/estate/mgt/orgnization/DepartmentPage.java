package com.lyun.estate.mgt.orgnization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by yanghong on 04/01/2017.
 */
@Controller
@RequestMapping("/org")
public class DepartmentPage {

    @GetMapping("/department")
    public ModelAndView index() {
        HashMap<String, String> params = new HashMap<>();
        params.put("message", "message");
        return new ModelAndView("/org/department", params);
    }
}

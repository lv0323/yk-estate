package com.lyun.estate.mgt.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
/**
 * Created by yanghong on 1/19/17.
 */

@Controller
@RequestMapping("/org")
public class EmployeePage {
    @GetMapping("/employee")
    public ModelAndView index() {
        HashMap<String, String> params = new HashMap<>();
        params.put("message", "message");
        return new ModelAndView("/org/employee", params);
    }
}

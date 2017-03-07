package com.lyun.estate.mgt.showing;

import com.lyun.estate.biz.showing.def.ShowingDefine.Process;
import com.lyun.estate.biz.customer.def.CustomerDefine.Source;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yanghong on 3/2/17.
 */
@Controller
@RequestMapping("/propertyVisit")
public class ShowingPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/propertyVisit")
    public ModelAndView propertyVisit() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        params.put("showingOperation", Arrays.asList(Process.values()));
        return new ModelAndView("/propertyVisit/propertyVisit", params);
    }

    @GetMapping("/addPropertyVisit")
    public ModelAndView addPropertyVisit() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        params.put("customerSource", Arrays.asList(Source.values()));
        return new ModelAndView("/propertyVisit/addPropertyVisit", params);
    }
}

package com.lyun.estate.mgt.tool;

import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("/tool")
public class ToolPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/myProfile")
    public ModelAndView myProfile() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/tool/myProfile", params);
    }
}

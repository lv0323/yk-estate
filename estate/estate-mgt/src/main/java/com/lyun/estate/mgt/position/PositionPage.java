package com.lyun.estate.mgt.position;

import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyun.estate.biz.position.def.PositionType;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yanghong on 04/01/2017.
 */
@Controller
@RequestMapping("/org")
public class PositionPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/position")
    public ModelAndView index() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("positionTypeList", Arrays.asList(PositionType.values()));
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/org/position/position", params);
    }
}

package com.lyun.estate.mgt.employee;

import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyun.estate.biz.position.def.PositionType;
import com.lyun.estate.biz.employee.def.WorkingStatus;
import com.lyun.estate.biz.support.def.Gender;
import java.util.Arrays;
import java.util.HashMap;
/**
 * Created by yanghong on 1/19/17.
 */

@Controller
@RequestMapping("/org")
public class EmployeePage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/employee")
    public ModelAndView index() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("positionTypeList", Arrays.asList(PositionType.values()));
        params.put("workingStatusList", Arrays.asList(WorkingStatus.values()));
        params.put("gender", Arrays.asList(Gender.values()));
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/org/employee/employee", params);
    }
}

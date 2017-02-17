package com.lyun.estate.mgt.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lyun.estate.biz.position.def.PositionType;
import com.lyun.estate.biz.employee.def.WorkingStatus;
import com.lyun.estate.biz.employee.def.Gender;
import java.util.Arrays;
import java.util.HashMap;
/**
 * Created by yanghong on 1/19/17.
 */

@Controller
@RequestMapping("/org")
public class EmployeePage {
    @GetMapping("/employee")
    public ModelAndView index() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("positionTypeList", Arrays.asList(PositionType.values()));
        params.put("workingStatusList", Arrays.asList(WorkingStatus.values()));
        params.put("gender", Arrays.asList(Gender.values()));
        return new ModelAndView("/org/employee/employee", params);
    }
}

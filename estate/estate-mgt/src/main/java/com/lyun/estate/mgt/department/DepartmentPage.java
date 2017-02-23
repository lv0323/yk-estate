package com.lyun.estate.mgt.department;

import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/department")
    public ModelAndView department() {
        HashMap<String, Object> params = new HashMap<>();
       params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/org/department/department", params);
    }

    @GetMapping("/addDepartment")
    public ModelAndView addDepartment() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/org/department/addDepartment", params);
    }

    @GetMapping("/departmentDetail")
    public ModelAndView departmentDetail() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/org/department/departmentDetail", params);
    }

    @GetMapping("/editDepartmentDetail")
    public ModelAndView editDepartmentDetail() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/org/department/editDepartmentDetail", params);
    }
}

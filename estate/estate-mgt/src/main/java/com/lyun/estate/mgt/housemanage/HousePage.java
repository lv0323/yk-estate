package com.lyun.estate.mgt.housemanage;

import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import com.lyun.estate.mgt.housedict.service.HouseDictMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by yanghong on 04/01/2017.
 */
@Controller
@RequestMapping("/houseManage")
public class HousePage {
    @Autowired
    private HouseDictMgtService houseDictMgtService;
    @Autowired
    private EmployeeMgtService service;


    @GetMapping("/houseList")
    public ModelAndView houseList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/houseManage/houseList", params);
    }

    @GetMapping("/addHouse")
    public ModelAndView addHouse() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        params.put("xiaoQuOptions", houseDictMgtService.xiaoQuOptions(null));
        return new ModelAndView("/houseManage/addHouse", params);
    }
}

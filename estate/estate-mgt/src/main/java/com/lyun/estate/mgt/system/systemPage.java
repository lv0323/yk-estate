package com.lyun.estate.mgt.system;

import com.lyun.estate.biz.fang.def.HouseType;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lyun.estate.biz.audit.def.AuditSubject;
import java.util.Arrays;
import java.util.HashMap;


@Controller
@RequestMapping("/system")
public class systemPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/operationLog")
    public ModelAndView operationLog() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        params.put("AuditSubjectList", Arrays.asList(AuditSubject.values()));
        return new ModelAndView("/system/operationLog", params);
    }
    @GetMapping("/estateDictionary")
    public ModelAndView houseDictionary() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("houseTypes", Arrays.asList(HouseType.values()));
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/system/estateDictionary/list", params);
    }

    @GetMapping("/estateDictionary/detail")
    public ModelAndView houseDictionaryItem() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("houseTypes", Arrays.asList(HouseType.values()));
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/system/estateDictionary/detail", params);
    }
}

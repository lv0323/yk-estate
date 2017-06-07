package com.lyun.estate.mgt.fangcollect;


import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.spec.fang.mgt.def.TimeType;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("/fangCollection")
public class FangCollectPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/pool")
    public ModelAndView houseList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        params.put("houseTypes", Arrays.asList(HouseType.values()));
        params.put("bizType", Arrays.asList(BizType.values()));
        params.put("houseLevel", Arrays.asList(HouseLevel.values()));
        params.put("decorate", Arrays.asList(Decorate.values()));
        params.put("decorate", Arrays.asList(Decorate.values()));
        params.put("houseStatus", Arrays.asList(HouseStatus.values()));
        params.put("houseProcess", Arrays.asList(HouseProcess.values()));
        params.put("houseSubProcess", Arrays.asList(HouseSubProcess.values()));
        params.put("houseTag", Arrays.asList(HouseTag.values()));
        params.put("propertyType", Arrays.asList(PropertyType.values()));
        params.put("certifType", Arrays.asList(CertifType.values()));
        params.put("followType", Arrays.asList(FollowType.values()));
        params.put("timeType", Arrays.asList(TimeType.values()));
        params.put("delegateType", Arrays.asList(DelegateType.values()));
        params.put("mgtFangSummaryOrder", Arrays.asList(MgtFangSummaryOrder.values()));
        return new ModelAndView("/fangCollect/pool", params);
    }

    @GetMapping("/detail")
    public ModelAndView detail() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/fangCollect/detail", params);
    }

    @GetMapping("/blacklist")
    public ModelAndView blacklist() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/fangCollect/blacklist", params);
    }
}

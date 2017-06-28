package com.lyun.estate.mgt.approval;

import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by robin on 17/6/5.
 */
@Controller
@RequestMapping("/approval")
public class ApprovalPage {

    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/newForm")
    public ModelAndView houseList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/approval/newForm/index", params);
    }

    @GetMapping("/home")
    public ModelAndView home() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/approval/newForm/home", params);
    }

    @GetMapping("/leaving")
    public ModelAndView leaving() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/approval/newForm/leaving", params);
    }
    @GetMapping("/bizTrip")
    public ModelAndView bizTrip() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/approval/newForm/bizTrip", params);
    }

    @GetMapping("/coldVisit")
    public ModelAndView coldVisit() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/approval/newForm/coldVisit", params);
    }

    @GetMapping("/signing")
    public ModelAndView signing() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("companyType", Arrays.asList(CompanyDefine.Type.values()));
        return new ModelAndView("/approval/newForm/signing", params);
    }

    @GetMapping("/myList")
    public ModelAndView myList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        params.put("companyType", Arrays.asList(CompanyDefine.Type.values()));
        params.put("ApprovalType", Arrays.asList(ApprovalDefine.Type.values()));
        params.put("ApprovalStatus", Arrays.asList(ApprovalDefine.Status.values()));
        return new ModelAndView("/approval/myList/index", params);
    }

    @GetMapping("/list")
    public ModelAndView list() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        params.put("companyType", Arrays.asList(CompanyDefine.Type.values()));
        params.put("ApprovalType", Arrays.asList(ApprovalDefine.Type.values()));
        params.put("ApprovalStatus", Arrays.asList(ApprovalDefine.Status.values()));
        return new ModelAndView("/approval/list/index", params);
    }
}

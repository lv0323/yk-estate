package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.company.def.CompanyDefine.Type;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yanghong on 04/01/2017.
 */
@Controller
@RequestMapping("/franchisee")
public class CompanyPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/channelPartner")
    public ModelAndView channel() {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/channelPartner/index", params);
    }

    @GetMapping("/channelPartner/createChannelPartner")
    public ModelAndView createChannel() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        params.put("franchiseeType",Arrays.asList(Type.values()));
        return new ModelAndView("/franchisee/channelPartner/createChannelPartner", params);
    }

    @GetMapping("/channelPartner/detailChannelPartner")
    public ModelAndView detailChannel() {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/channelPartner/detail", params);
    }

    @GetMapping("/storePartner")
    public ModelAndView store() {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/storePartner/index", params);
    }

    @GetMapping("/storePartner/createStorePartner")
    public ModelAndView createStorePartner() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        params.put("franchiseeType", Arrays.asList(Type.values()));
        return new ModelAndView("/franchisee/storePartner/createStorePartner", params);
    }

    @GetMapping("/storePartner/detailStorePartner")
    public ModelAndView detailStore() {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/storePartner/detail", params);
    }

    @GetMapping("/regionAgent")
    public ModelAndView region() {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/regionAgent/index", params);
    }

    @GetMapping("/regionAgent/createRegionAgent")
    public ModelAndView createRegionAgent() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        params.put("franchiseeType", Arrays.asList(Type.values()));
        return new ModelAndView("/franchisee/regionAgent/createRegionAgent", params);
    }

    @GetMapping("/regionAgent/detailRegionAgent")
    public ModelAndView detailRegion() {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/regionAgent/detail", params);
    }

    @GetMapping("/company")
    public ModelAndView company() {
        HashMap<String, String> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/company", params);
    }

    @GetMapping("/addCompany")
    public ModelAndView addCompany() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
//        params.put("platformList", Arrays.asList(HouseTag.values()));
        return new ModelAndView("/franchisee/addCompany", params);
    }

    @GetMapping("/companyDetail")
    public ModelAndView companyDetail() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/companyDetail", params);
    }

    @GetMapping("/editCompanyDetail")
    public ModelAndView editCompanyDetail() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/franchisee/editCompanyDetail", params);
    }
}

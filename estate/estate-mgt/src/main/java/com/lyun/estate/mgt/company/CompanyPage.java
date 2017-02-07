package com.lyun.estate.mgt.company;

import com.lyun.estate.biz.fang.def.HouseTag;
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

    @GetMapping("/company")
    public ModelAndView company() {
        HashMap<String, String> params = new HashMap<>();
        params.put("message", "message");
        return new ModelAndView("/franchisee/company", params);
    }

    @GetMapping("/addCompany")
    public ModelAndView addCompany() {
        HashMap<String, Object> params = new HashMap<>();
//        params.put("platformList", Arrays.asList(HouseTag.values()));
        return new ModelAndView("/franchisee/addCompany", params);
    }

    @GetMapping("/companyDetail")
    public ModelAndView companyDetail() {
        HashMap<String, Object> params = new HashMap<>();
//        params.put("platformList", Arrays.asList(HouseTag.values()));
        return new ModelAndView("/franchisee/companyDetail", params);
    }
}

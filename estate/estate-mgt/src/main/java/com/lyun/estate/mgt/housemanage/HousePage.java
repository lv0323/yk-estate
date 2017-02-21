package com.lyun.estate.mgt.housemanage;

import com.lyun.estate.biz.housedict.service.HouseDictService;
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


    @GetMapping("/houseList")
    public ModelAndView houseList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("message", "message");
        return new ModelAndView("/houseManage/houseList", params);
    }
    @GetMapping("/addHouse")
    public ModelAndView addHouse() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("xiaoQuOptions",houseDictMgtService.xiaoQuOptions());
        return new ModelAndView("/houseManage/addHouse", params);
    }
}

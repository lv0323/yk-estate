package com.lyun.estate.mgt.houseManage;

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

    @GetMapping("/houseList")
    public ModelAndView company() {
        HashMap<String, String> params = new HashMap<>();
        params.put("message", "message");
        return new ModelAndView("/houseManage/houseList", params);
    }
}

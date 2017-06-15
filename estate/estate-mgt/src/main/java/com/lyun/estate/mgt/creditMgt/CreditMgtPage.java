package com.lyun.estate.mgt.creditMgt;

//import com.lyun.estate.biz.position.def.PositionType;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yanghong on 6/15/17.
 */
@Controller
@RequestMapping("/creditMgt")
public class CreditMgtPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/list")
    public ModelAndView agentList() {
        HashMap<String, Object> params = new HashMap<>();
        //params.put("positionTypeList", Arrays.asList(PositionType.values()));
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/creditMgt/list", params);
    }

    @GetMapping("/detail")
    public ModelAndView detailAgent() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/creditMgt/detail", params);
    }

    @GetMapping("/edit")
    public ModelAndView editAgent() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/creditMgt/edit", params);
    }
}

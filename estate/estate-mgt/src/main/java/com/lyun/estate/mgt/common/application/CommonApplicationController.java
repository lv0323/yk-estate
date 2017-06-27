package com.lyun.estate.mgt.common.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.lyun.estate.biz.application.entity.CommonApplicationEntity.Type.*;
import static com.lyun.estate.biz.application.entity.CommonApplicationEntity.Status.*;

@Controller
@RequestMapping("/application")
public class CommonApplicationController {

    @RequestMapping("/fang/management")
    public ModelAndView applicationManagement() {
        Map<String, Object> params = new HashMap();

        params.put("types", Arrays.asList(PAUSE_HOUSE, PUBLIC_HOUSE, UN_PUBLIC_HOUSE, PUBLISH_HOUSE, UN_PUBLISH_HOUSE));
        params.put("statusList", Arrays.asList(NEW, APPROVED, REJECTED, CLOSED));

        return new ModelAndView("/application/application-management", params);
    }

}

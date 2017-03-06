package com.lyun.estate.mgt.propertyVisit;

import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.fang.def.PriceUnit;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by yanghong on 3/2/17.
 */
@Controller
@RequestMapping("/propertyVisit")
public class propertyVisitPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/propertyVisit")
    public ModelAndView propertyVisit() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/propertyVisit/propertyVisit", params);
    }

    @GetMapping("/addPropertyVisit")
    public ModelAndView addPropertyVisit() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        params.put("rentPriceUnit", PriceUnit.getByBizType(BizType.RENT));
        params.put("sellPriceUnit", PriceUnit.getByBizType(BizType.SELL));
        return new ModelAndView("/propertyVisit/addPropertyVisit", params);
    }
}

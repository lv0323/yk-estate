package com.lyun.estate.mgt.contract;
import com.lyun.estate.biz.contract.def.ContractDefine.Process;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import com.lyun.estate.biz.fang.def.HouseType;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.fang.def.PriceUnit;
import com.lyun.estate.biz.support.def.IdentitySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashMap;
/**
 * Created by yanghong on 3/13/17.
 */
@Controller
@RequestMapping("/contract")
public class ContractPage {
    @Autowired
    private EmployeeMgtService service;

    @GetMapping("/deal")
    public ModelAndView deal() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        params.put("contractOperation", Arrays.asList(Process.values()));
        params.put("houseType",Arrays.asList(HouseType.values()));
        params.put("bizType",Arrays.asList(BizType.values()));
        return new ModelAndView("/contract/deal/deal", params);
    }

    @GetMapping("/addDeal")
    public ModelAndView addDeal() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        params.put("houseType",Arrays.asList(HouseType.values()));
        params.put("bizType",Arrays.asList(BizType.values()));
        params.put("priceUnit",Arrays.asList(PriceUnit.values()));
        params.put("identitySource",Arrays.asList(IdentitySource.values()));
        return new ModelAndView("/contract/deal/addDeal", params);
    }

    @GetMapping("/viewDeal")
    public ModelAndView viewDeal() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl",service.getAvatar());
        params.put("username",service.getUsername());
        return new ModelAndView("/contract/deal/viewDeal", params);
    }

}

package com.lyun.estate.mgt.fang;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import com.lyun.estate.mgt.housedict.service.HouseDictMgtService;
import com.lyun.estate.biz.spec.fang.mgt.def.TimeType;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by robin on 17/2/24.
 */
@Controller
@RequestMapping("/fangManage")
public class FangPage {
    @Autowired
    private HouseDictMgtService houseDictMgtService;
    @Autowired
    private EmployeeMgtService service;
    @Autowired
    private MgtFangService mgtFangService;


    @GetMapping("/list")
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
        params.put("houseTag", Arrays.asList(HouseTag.values()));
        params.put("propertyType", Arrays.asList(PropertyType.values()));
        params.put("certifType", Arrays.asList(CertifType.values()));
        params.put("followType", Arrays.asList(FollowType.values()));
        params.put("timeType", Arrays.asList(TimeType.values()));
        params.put("delegateType", Arrays.asList(DelegateType.values()));
        params.put("mgtFangSummaryOrder", Arrays.asList(MgtFangSummaryOrder.values()));
        return new ModelAndView("/fang/list", params);
    }

    @GetMapping("/create")
    public ModelAndView addHouse() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("houseTypes", Arrays.asList(HouseType.values()));
        params.put("orientation", Arrays.asList(Orientation.values()));
        params.put("structureType", Arrays.asList(StructureType.values()));
        params.put("rentPriceUnit", PriceUnit.getByBizType(BizType.RENT));
        params.put("sellPriceUnit", PriceUnit.getByBizType(BizType.SELL));
        params.put("houseLevel", Arrays.asList(HouseLevel.values()));
        params.put("delegateType", Arrays.asList(DelegateType.values()));
        params.put("showing", Arrays.asList(Showing.values()));
        params.put("houseStatus", Arrays.asList(HouseStatus.values()));
        params.put("houseSource", Arrays.asList(HouseSource.values()));
        params.put("certifType", Arrays.asList(CertifType.values()));
        params.put("propertyType", Arrays.asList(PropertyType.values()));
        params.put("decorate", Arrays.asList(Decorate.values()));
        params.put("heatingType", Arrays.asList(HeatingType.values()));
        params.put("taxesWilling", Arrays.asList(TaxesWilling.values()));
        params.put("commissionWilling", Arrays.asList(CommissionWilling.values()));
        params.put("username", service.getUsername());
        return new ModelAndView("/fang/create", params);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam Long id) {
        Fang fang = mgtFangService.getFangBase(id);
        System.out.print(fang.getHouseType().getSubTypes());
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("houseTypes", Arrays.asList(HouseType.values()));
        params.put("houseSubType", fang.getHouseSubType().name());
        params.put("houseSubTypes", fang.getHouseType().getSubTypes());
        params.put("orientation", Arrays.asList(Orientation.values()));
        params.put("structureType", Arrays.asList(StructureType.values()));
        params.put("rentPriceUnit", PriceUnit.getByBizType(BizType.RENT));
        params.put("sellPriceUnit", PriceUnit.getByBizType(BizType.SELL));
        params.put("houseLevel", Arrays.asList(HouseLevel.values()));
        params.put("delegateType", Arrays.asList(DelegateType.values()));
        params.put("showing", Arrays.asList(Showing.values()));
        params.put("houseStatus", Arrays.asList(HouseStatus.values()));
        params.put("houseSource", Arrays.asList(HouseSource.values()));
        params.put("certifType", Arrays.asList(CertifType.values()));
        params.put("propertyType", Arrays.asList(PropertyType.values()));
        params.put("decorate", Arrays.asList(Decorate.values()));
        params.put("heatingType", Arrays.asList(HeatingType.values()));
        params.put("taxesWilling", Arrays.asList(TaxesWilling.values()));
        params.put("commissionWilling", Arrays.asList(CommissionWilling.values()));
        params.put("followType", Arrays.asList(FollowType.values()));
        params.put("username", service.getUsername());
        if(fang.getBizType().equals(BizType.RENT)){
            params.put("priceUnit", PriceUnit.getByBizType(BizType.RENT));

        }else{
            params.put("priceUnit", PriceUnit.getByBizType(BizType.SELL));
        }
        return new ModelAndView("/fang/detail", params);
    }

    @GetMapping("/follow")
    public ModelAndView follow() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("followType", Arrays.asList(FollowType.values()));
        params.put("username", service.getUsername());
        return new ModelAndView("/fang/follow", params);
    }

    @GetMapping("/survey")
    public ModelAndView survey() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("avatarUrl", service.getAvatar());
        params.put("username", service.getUsername());
        return new ModelAndView("/fang/survey", params);
    }
}

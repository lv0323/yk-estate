package com.lyun.estate.rest.information;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.information.def.InfoBusinessType;
import com.lyun.estate.biz.information.def.InfoContentType;
import com.lyun.estate.biz.information.entity.InformationCounterResource;
import com.lyun.estate.biz.information.entity.InformationResource;
import com.lyun.estate.biz.information.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jesse on 2017/1/19.
 */
@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping("/counter")
    InformationCounterResource getInfoCounter() {
        return informationService.getCurrentUserInfoCounter();
    }

    @GetMapping("/show")
    List<InformationResource> getInfoByBusinessType( @RequestParam(required = true) InfoBusinessType businessType,
                                                     @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return informationService.getCurrentUserInfoByBusinessType(businessType, pageBounds);
    }

    //TODO 不在rest提供API
    @GetMapping("/create")
    boolean createInfo(@RequestParam(required = true) String infoTitle, @RequestParam(required = true) String infoSummary, @RequestParam(required = true) String infoContent,
                       @RequestParam(required = true) InfoContentType contentType, @RequestParam(required = true) InfoBusinessType businessType) {
        return informationService.createCurrentUserInfo(infoTitle, infoSummary, infoContent, contentType, businessType);
    }
}

package com.lyun.estate.rest.information;

import com.lyun.estate.biz.information.def.InfoBusinessType;
import com.lyun.estate.biz.information.def.InfoContentType;
import com.lyun.estate.biz.information.entity.InformationCounterResource;
import com.lyun.estate.biz.information.entity.InformationResource;
import com.lyun.estate.biz.information.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jesse on 2017/1/19.
 */
@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping("/counter")
    InformationCounterResource getInfoCounter(@RequestParam Long receiverId) {
        return informationService.getInfoCounter(receiverId);
    }

    @GetMapping("/show")
    List<InformationResource> getInfoByBusinessType(Long receiverId, InfoBusinessType businessType) {
        return informationService.getInfoByBusinessType(receiverId, businessType);
    }

    @GetMapping("/create")
    boolean createInfo(String infoTitle, String infoSummary, String infoContent, InfoContentType contentType, InfoBusinessType businessType, Long sender, Long receiver) {
        return informationService.createInfo(infoTitle, infoSummary, infoContent, contentType, businessType, sender, receiver);
    }
}

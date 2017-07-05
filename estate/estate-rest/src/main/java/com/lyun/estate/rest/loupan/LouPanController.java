package com.lyun.estate.rest.loupan;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.loupan.domain.LouPanSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jeffrey on 2017-07-05.
 */
@RestController
@RequestMapping("loupan")
public class LouPanController {


    @GetMapping("list")
    PageList<LouPanSummary> list(
            @RequestParam(required = false) Long cityId,
            @RequestParam(required = false) Long districtId,
            @RequestParam(required = false) Long subDistrictId) {
        return null;
    }


}

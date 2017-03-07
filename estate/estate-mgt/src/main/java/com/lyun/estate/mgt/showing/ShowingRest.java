package com.lyun.estate.mgt.showing;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.customer.def.CustomerDefine;
import com.lyun.estate.biz.fang.def.Showing;
import com.lyun.estate.biz.showing.def.ShowingDefine;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-03-07.
 */
@RestController
@RequestMapping("showing")
public class ShowingRest {

    @PostMapping("create")
    public Showing createShowing(@RequestParam Long fangId,
                                 @RequestParam String customerName,
                                 @RequestParam CustomerDefine.Source customerSource,
                                 @RequestParam String customerMobile) {
        return null;
    }

    @PostMapping("close")
    public Showing closeShowing(@RequestParam Long showingId,
                                @RequestParam ShowingDefine.Process process) {

        return null;
    }

    @GetMapping("list")
    public PageList<Showing> listShowing(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date minCreateDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date maxCreateDate,
            @RequestParam(required = false) ShowingDefine.Process process,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Boolean children,
            @RequestParam(required = false) Long employeeId) {
        return null;
    }

}

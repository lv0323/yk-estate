package com.lyun.estate.mgt.test;

import com.lyun.estate.mgt.test.service.TestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-27.
 */
@RestController
@RequestMapping("api/report")
public class TestRest {

    @Autowired
    private TestReportService testReportService;


    @GetMapping("/operation/getXiaoQu")
    public String reviewEfficiencyReportQuery(@RequestParam(name = "domainType", required = false) String domainType,
                                              @RequestParam(name = "offset") Long offset,
                                              @RequestParam(name = "limit") Long limit) {
        return testReportService.getXiaoQuReportQuery(null, null, domainType, offset, limit);
    }

    @GetMapping("/operation/getXiaoQu/export")
    public void reviewEfficiencyReportExport(@RequestParam(name = "domainType", required = false) String domainType,
                                             HttpServletResponse response) {
        testReportService.getXiaoQuReportExport(null, null, domainType, response);
    }

}

package com.lyun.estate.mgt.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.lyun.estate.biz.audit.def.AuditSubject;
import java.util.Arrays;
import java.util.HashMap;


@Controller
@RequestMapping("/system")
public class systemPage {
    @GetMapping("/operationLog")
    public ModelAndView operationLog() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("AuditSubjectList", Arrays.asList(AuditSubject.values()));
        return new ModelAndView("/system/operationLog", params);
    }
}

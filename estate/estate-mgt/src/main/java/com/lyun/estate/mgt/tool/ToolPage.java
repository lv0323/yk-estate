package com.lyun.estate.mgt.tool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("/tool")
public class ToolPage {
    @GetMapping("/myProfile")
    public ModelAndView myProfile() {
        HashMap<String, Object> params = new HashMap<>();
        return new ModelAndView("/tool/myProfile", params);
    }
}

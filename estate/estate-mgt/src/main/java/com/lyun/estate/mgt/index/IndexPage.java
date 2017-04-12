package com.lyun.estate.mgt.index;

import com.lyun.estate.mgt.supports.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class IndexPage {

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        if (Objects.nonNull(request.getSession().getAttribute(Constant.SESSION_IS_LOGIN))
                && (Boolean) request.getSession().getAttribute(Constant.SESSION_IS_LOGIN)) {
            return new ModelAndView("redirect:/fangManage/list?target=.fang");
        } else {
            response.setHeader("X-PAGE-FLAG", "/index");
            HashMap<String, String> params = new HashMap<>();
            return new ModelAndView("index", params);
        }
    }
}

package com.lyun.estate.mgt.advice;

import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import com.lyun.estate.mgt.supports.Constant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private MgtContext mgtContext;

    public AuthInterceptor(MgtContext mgtContext) {
        this.mgtContext = mgtContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute(Constant.SESSION_IS_LOGIN) == null
                || !((Boolean) request.getSession().getAttribute(Constant.SESSION_IS_LOGIN))) {
            response.sendRedirect("/index");
            return false;
        } else {
            if (request.getSession().getAttribute(Constant.SESSION_OPERATOR) != null) {
                mgtContext.setOperator((Operator) request.getSession().getAttribute(Constant.SESSION_OPERATOR));
            }
        }
        return true;
    }
}

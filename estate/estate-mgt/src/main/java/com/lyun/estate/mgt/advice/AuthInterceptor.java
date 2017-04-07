package com.lyun.estate.mgt.advice;

import com.lyun.estate.biz.ipwhitelist.service.IpWhiteListService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import com.lyun.estate.mgt.supports.Constant;
import eu.bitwalker.useragentutils.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MgtContext mgtContext;
    @Autowired
    private IpWhiteListService ipWhiteListService;

    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute(Constant.SESSION_IS_LOGIN) == null
                || !((Boolean) request.getSession().getAttribute(Constant.SESSION_IS_LOGIN))) {
            response.sendRedirect("/index");
            return false;
        } else {
            Operator operator = (Operator) request.getSession().getAttribute(Constant.SESSION_OPERATOR);
            if (operator == null) {
                logger.warn("{} , operator is null, fail to access", mgtContext.getCorrelationId());
                return false;
            }
            mgtContext.setOperator(operator);

            // 公司ip地址白名单功能开启
            // 1.如果ip地址符合，则通过
            // 2.设备Id与绑定id一致，也通过
            if (operator.getCompanyIpCheck()) {
                // 如果是系统Admin
                if (operator.getSysAdmin()) {
                    return true;
                }
                if (ipWhiteListService.ipWhiteList(operator.getCompanyId()).contains(mgtContext.getUserAddress())) {
                    return true;
                }
                if (mgtContext.getDeviceType() == DeviceType.MOBILE) {
                    Cookie deviceIdCookie = Arrays.stream(request.getCookies())
                            .filter(t -> Objects.equals(t.getName(), "deviceId"))
                            .findAny()
                            .orElse(null);
                    if (deviceIdCookie != null
                            && Objects.equals(deviceIdCookie.getValue(), operator.getDeviceId())) {
                        return true;
                    }
                }
                request.getSession().invalidate();
                throw new EstateException(ExCode.IP_AND_DEVICE_ERROR);
            }
        }
        return true;
    }
}

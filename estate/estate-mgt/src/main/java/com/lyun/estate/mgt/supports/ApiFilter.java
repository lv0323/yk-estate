package com.lyun.estate.mgt.supports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class ApiFilter implements Filter {

    @Autowired
    private ApiListener[] listeners;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Arrays.asList(listeners).forEach(listener ->
                    listener.onRequest((HttpServletRequest) request, (HttpServletResponse) response));
            chain.doFilter(request, response);
            Arrays.asList(listeners).forEach(listener -> listener.onResponse((HttpServletResponse) response));
        } finally {
            Arrays.asList(listeners).forEach(ApiListener::onComplete);
        }
    }

    @Override
    public void destroy() {
    }
}

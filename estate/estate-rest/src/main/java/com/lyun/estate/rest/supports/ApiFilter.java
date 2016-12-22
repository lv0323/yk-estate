package com.lyun.estate.rest.supports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class ApiFilter implements Filter {

    @Autowired
    private ApiListener[] listeners;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Arrays.asList(listeners).stream().forEach(listener -> {
                listener.onRequest((HttpServletRequest) request, (HttpServletResponse) response);
            });
            chain.doFilter(request, response);
            Arrays.asList(listeners).stream().forEach(listener -> {
                listener.onResponse((HttpServletResponse) response);
            });
        } finally {
            Arrays.asList(listeners).stream().forEach(listener -> {
                listener.onComplete();
            });
        }
    }

    @Override
    public void destroy() {}
}

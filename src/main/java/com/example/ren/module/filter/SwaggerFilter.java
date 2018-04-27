package com.example.ren.module.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用于登录拦截
 *
 * @author qiang.ren on 2018/3/26
 */
@Order(value = 3)
@WebFilter(filterName = "swaggerFilter", urlPatterns = "/swagger**")
public class SwaggerFilter implements Filter {

    public static final String SWAGGER_LOGIN_FLAG = "SWAGGER_LOGIN_OK";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();
        Object session = httpSession.getAttribute(SWAGGER_LOGIN_FLAG);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "swagger/login");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}

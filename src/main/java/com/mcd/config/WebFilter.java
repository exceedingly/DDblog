package com.mcd.config;


import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by MaoChenDong on 2020/3/29.
 */
@javax.servlet.annotation.WebFilter(urlPatterns = "/*")
@Order(1)
class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("getFilterName:"+filterConfig.getFilterName());//返回<filter-name>元素的设置值。
        System.out.println("getServletContext:"+filterConfig.getServletContext());//返回FilterConfig对象中所包装的ServletContext对象的引用。
        System.out.println("getInitParameter:"+filterConfig.getInitParameter("cacheTimeout"));//用于返回在web.xml文件中为Filter所设置的某个名称的初始化的参数值
        System.out.println("getInitParameterNames:"+filterConfig.getInitParameterNames());//返回一个Enumeration集合对象。


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}

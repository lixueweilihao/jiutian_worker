package org.web.websocket.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@WebFilter(urlPatterns = {"/*"})
public class DispatcherFilter implements Filter {

    private Logger logger = Logger.getLogger(DispatcherFilter.class);

    public void destroy() {
        // Auto-generated method stub

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
         FilterChain chain) throws IOException, ServletException {
        // Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) arg0;
        logger.info("Request URL: " + request.getRequestURL());
        chain.doFilter(arg0, arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
        // Auto-generated method stub
        System.out.println("----FilterDemo02过滤器初始化----");
        String filterName = arg0.getFilterName();
        // 得到在web.xml文件中配置的初始化参数
        String initParam1 = arg0.getInitParameter("name");
        String initParam2 = arg0.getInitParameter("like");
        // 返回过滤器的所有初始化参数的名字的枚举集合。
        Enumeration<String> initParameterNames = arg0.getInitParameterNames();

        System.out.println(filterName);
        System.out.println(initParam1);
        System.out.println(initParam2);
        while (initParameterNames.hasMoreElements()) {
            String paramName = (String) initParameterNames.nextElement();
            System.out.println(paramName);
        }

    }

}

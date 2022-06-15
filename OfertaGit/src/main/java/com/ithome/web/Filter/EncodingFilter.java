package com.ithome.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


public class EncodingFilter implements Filter {
    private String encoding;
    private FilterConfig filterConfig;

    public EncodingFilter() {
    }

    public void init(FilterConfig filterconfig) {
        filterConfig = filterconfig;
        encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
            throws IOException, ServletException {
        servletrequest.setCharacterEncoding(encoding);
        filterchain.doFilter(servletrequest, servletresponse);
    }

    public void destroy() {
    }


}

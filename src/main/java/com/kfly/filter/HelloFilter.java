package com.kfly.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Kfly on 2016/4/13.
 */
public class HelloFilter implements Filter {

    private String name;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("hahah");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        name = "hello";
        System.out.println(name);
    }

}

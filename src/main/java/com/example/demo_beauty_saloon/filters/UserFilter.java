package com.example.demo_beauty_saloon.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userrole") == null
                || session.getAttribute("userrole") != "client") {
            RequestDispatcher disp = req.getRequestDispatcher("/errors/error-403.jsp");
            disp.forward(req, res);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
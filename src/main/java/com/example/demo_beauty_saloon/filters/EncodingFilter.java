package com.example.demo_beauty_saloon.filters;

import javax.servlet.*;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Encoding filter.
 *
 */
public class EncodingFilter implements Filter {

    private static final Logger log = Logger.getLogger(EncodingFilter.class);

    private static final String DEFAULT_ENCODING = "UTF-8";

    public void destroy() {
        log.debug("Filter destruction starts");
        // do nothing
        log.debug("Filter destruction finished");
    }

    /**
     * Check Encoding in web.xml
     *
     * @param fConfig
     */
    public void init(FilterConfig fConfig) throws ServletException {
        log.debug("Filter initialization starts");
        log.trace("Encoding from web.xml --> " + fConfig.getInitParameter("encoding"));
        log.debug("Filter initialization finished");
    }

    /**
     * Change Encoding
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        log.debug("Filter starts");
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding(DEFAULT_ENCODING);
        servletRequest.setCharacterEncoding(DEFAULT_ENCODING);

        log.debug("Filter finished");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
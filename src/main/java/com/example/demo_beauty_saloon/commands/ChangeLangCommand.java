package com.example.demo_beauty_saloon.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Change Language Command. Changes WEB-APP Language
 */
public class ChangeLangCommand extends Command {

    private static final Logger log = Logger.getLogger(ChangeLangCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        request.setCharacterEncoding("UTF-8");
        String language = request.getParameter("lang");
        request.getSession().setAttribute("language", language);
        log.debug("Set the request attribute: language --> " + language);
        log.debug("Language set to " + language);
        log.debug("Command executed");
        return "/index.jsp";
    }
}

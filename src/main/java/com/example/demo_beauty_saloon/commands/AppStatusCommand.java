package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.AppointmentDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Appointment Status Command. Changes Appointment status
 */
public class AppStatusCommand extends Command {

    private static final Logger log = Logger.getLogger(AppStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        AppointmentDao app = new AppointmentDao();
        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("appStatus"));
        HttpSession session = request.getSession();
        if (app.updateAppStatus(Long.parseLong(request.getParameter("id")),
                request.getParameter("appStatus")) > 0) {
            PrintWriter out = response.getWriter();
            request.getRequestDispatcher("index.jsp").include(request, response);
            log.debug("Setting response");

            out.print("<div align=\"center\">");
            if (session.getAttribute("language") == null) {
                out.print("<h2>Статус записи успешно изменен!</h2>");
            } else if (session.getAttribute("language").equals("ru")) {
                out.print("<h2>Статус записи успешно изменен!</h2>");
            } else if (session.getAttribute("language").equals("en")) {
                out.print("<h2>Successfully changed appointment status!</h2>");
            }
            out.print("</div>");

            out.close();
        } else {
            PrintWriter out = response.getWriter();
            request.getRequestDispatcher("index.jsp").include(request, response);
            log.debug("Setting response");

            out.print("<div align=\"center\">");
            if (session.getAttribute("language") == null) {
                out.print("<h2>Возникла ошибка!</h2>");
            } else if (session.getAttribute("language").equals("ru")) {
                out.print("<h2>Возникла ошибка!</h2>");
            } else if (session.getAttribute("language").equals("en")) {
                out.print("<h2>Error occurred!</h2>");
            }
            out.print("</div>");

            out.close();
        }
        log.debug("Command executed");
        return "/index.jsp";
    }
}

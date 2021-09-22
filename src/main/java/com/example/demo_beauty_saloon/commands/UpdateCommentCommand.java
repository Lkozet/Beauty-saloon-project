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
 * Update Comment Command. Change Appointment comment
 *
 */
public class UpdateCommentCommand extends Command {

    private static final Logger log = Logger.getLogger(UpdateCommentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        AppointmentDao app = new AppointmentDao();
        long id = Long.parseLong(request.getParameter("id"));
        String comment = request.getParameter("comment");
        HttpSession session = request.getSession();
        if (app.updateAppComment(id, comment) > 0) {
            app.updateAppEmailNeeded(id, "false");
            PrintWriter out = response.getWriter();
            request.getRequestDispatcher("index.jsp").include(request, response);

            out.print("<div align=\"center\">");
            if (session.getAttribute("language") == null) {
                out.print("<h2>Спасибо за обзор!</h2>");
            } else if (session.getAttribute("language").equals("ru")) {
                out.print("<h2>Спасибо за обзор!</h2>");
            } else if (session.getAttribute("language").equals("en")) {
                out.print("<h2>Thank you for review!</h2>");
            }
            out.print("</div>");

            out.close();
        } else {
            PrintWriter out = response.getWriter();
            request.getRequestDispatcher("index.jsp").include(request, response);

            out.print("<div align=\"center\">");
            out.print("<h2>Error occurred!</h2>");
            out.print("</div>");

            out.close();
        }
        log.debug("Command executed");
        return "/index.jsp";
    }
}

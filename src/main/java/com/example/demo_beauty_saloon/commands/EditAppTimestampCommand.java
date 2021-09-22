package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.AppointmentDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Edit Appointment Timestamp Command. Changes Appointment timestamp if possible
 *
 */
public class EditAppTimestampCommand extends Command {

    private static final Logger log = Logger.getLogger(EditAppTimestampCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        AppointmentDao app = new AppointmentDao();
        HttpSession session = request.getSession();
        log.info("New timestamp" +request.getParameter("newTimeStamp"));
        if (app.updateAppTimestamp(Long.parseLong(request.getParameter("id")),
                LocalDateTime.parse(request.getParameter("newTimeStamp"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).plusHours(3)) > 0) {
            PrintWriter out = response.getWriter();
            request.getRequestDispatcher("index.jsp").include(request, response);
            log.debug("Setting response");

            out.print("<div align=\"center\">");
            if (session.getAttribute("language") == null) {
                out.print("<h2>Время успешно изменено!</h2>");
            } else if (session.getAttribute("language").equals("ru")) {
                out.print("<h2>Время успешно изменено!</h2>");
            } else if (session.getAttribute("language").equals("en")) {
                out.print("<h2>Successfully changed timestamp!</h2>");
            }
            out.print("</div>");

            out.close();
        } else {
            PrintWriter out = response.getWriter();
            request.getRequestDispatcher("index.jsp").include(request, response);

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

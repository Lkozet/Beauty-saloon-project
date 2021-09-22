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
 * Delete Appointment Command. Deletes requested Appointment
 *
 */
public class DeleteAppCommand extends Command {

    private static final Logger log = Logger.getLogger(DeleteAppCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        AppointmentDao app = new AppointmentDao();
        app.deleteApp(Long.parseLong(request.getParameter("id")));

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        request.getRequestDispatcher("index.jsp").include(request, response);
        log.debug("Setting response");
        out.print("<div align=\"center\">");
        if (session.getAttribute("language") == null) {
            out.print("<h2>Успешно удалено!</h2>");
        } else if (session.getAttribute("language").equals("ru")) {
            out.print("<h2>Успешно удалено!</h2>");
        } else if (session.getAttribute("language").equals("en")) {
            out.print("<h2>Successfully deleted!</h2>");
        }
        out.print("</div>");

        out.close();
        log.debug("Command executed");
        return "/index.jsp";
    }
}

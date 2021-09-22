package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.AppointmentDao;
import com.example.demo_beauty_saloon.dao.UserDao;
import com.example.demo_beauty_saloon.entity.Appointment;
import com.example.demo_beauty_saloon.entity.User;
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
 * Register Appointment Command. Register appointments with given parameters
 */
public class RegisterAppCommand extends Command {

    private static final Logger log = Logger.getLogger(RegisterAppCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        request.setCharacterEncoding("UTF-8");
        UserDao my = new UserDao();
        HttpSession session = request.getSession();
        User user = my.findUserByLogin(session.getAttribute("username").toString());
        long userId = user.getId();
        long serviceId = Long.parseLong(request.getParameter("serviceId"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.parse(request.getParameter("timeStamp"), formatter);
        long masterId = Long.parseLong(request.getParameter("masterId"));
        Appointment appointment = new Appointment();

        appointment.setUserId(userId);
        appointment.setServiceId(serviceId);
        appointment.setTimestamp(timeStamp.plusHours(3));
        appointment.setMasterId(masterId);
        appointment.setPaymentStatus("false");
        System.out.println(appointment);

        response.setContentType("text/html; charset=utf-8");
        AppointmentDao app = new AppointmentDao();
        try {
            PrintWriter out = response.getWriter();
            request.getRequestDispatcher("index.jsp").include(request, response);
            if (app.registerApp(appointment) > 0) {
                log.debug("Setting response");

                out.print("<div align=\"center\">");
                if (session.getAttribute("language") == null) {
                    out.print("<h2>Запись введена успешно!</h2>");
                } else if (session.getAttribute("language").equals("ru")) {
                    out.print("<h2>Запись введена успешно!</h2>");
                } else if (session.getAttribute("language").equals("en")) {
                    out.print("<h2>Record entered successfully!</h2>");
                }
                out.print("</div>");

                out.close();
                log.debug("Command executed");
                return "/index.jsp";
            } else {
                out.print("<div align=\"center\">");
                out.print("<h2>Совпадение в базе!</h2>");
                out.print("</div>");

                out.close();
                log.debug("Command executed");
                return "/client/bookappointment.jsp";
            }
        } catch (Exception e) {
            log.error(e);
            log.debug("Command executed");
            return "/errors/error-500.jsp";
        }
    }
}

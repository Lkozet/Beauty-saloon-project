package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.AppointmentDao;
import com.example.demo_beauty_saloon.entity.Appointment;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Admin command. Get all appointments for admin management
 *
 */
public class AdminCommand extends Command {

    private static final Logger log = Logger.getLogger(AdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        response.setContentType("text/html; charset=utf-8");
        AppointmentDao app = new AppointmentDao();
        ArrayList<Appointment> allAppointments = app.getAllAppointments();
        request.setAttribute("allAppointments", allAppointments);
        log.debug("Set the request attribute: allAppointments --> " + allAppointments);
        log.debug("Command executed");
        return "/admin/admin_panel.jsp";
    }
}

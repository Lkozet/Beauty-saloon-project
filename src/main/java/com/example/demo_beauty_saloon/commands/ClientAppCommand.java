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
import java.util.ArrayList;

/**
 * Client Appointments Command. Get all this.User appointments
 *
 */
public class ClientAppCommand extends Command {

    private static final Logger log = Logger.getLogger(ClientAppCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        response.setContentType("text/html; charset=utf-8");
        UserDao my = new UserDao();
        AppointmentDao app = new AppointmentDao();
        HttpSession session = request.getSession();
        User user = my.findUserByLogin(session.getAttribute("username").toString());
        ArrayList<Appointment> appointments2 = app.findAppByUId(user.getId());
        request.setAttribute("appointments2", appointments2);
        log.debug("Set the request attribute: appointments2 --> " + appointments2);
        log.debug("Command executed");
        return "/client/client_panel.jsp";
    }
}

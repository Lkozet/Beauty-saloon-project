package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.AppointmentDao;
import com.example.demo_beauty_saloon.dao.MasterDao;
import com.example.demo_beauty_saloon.entity.Appointment;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Master Appointments Command. Get all this.Master appointments
 *
 */
public class MasterAppCommand extends Command {

    private static final Logger log = Logger.getLogger(MasterAppCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        response.setContentType("text/html; charset=utf-8");
        MasterDao my = new MasterDao();
        AppointmentDao app = new AppointmentDao();
        HttpSession session = request.getSession();
        long masterId = my.findMasterIdByLogin(session.getAttribute("username").toString());
        ArrayList<Appointment> appointments = app.findAppByMId(masterId);
        request.setAttribute("appointments", appointments);
        log.trace("Set the request attribute: appointments --> " + appointments);
        log.debug("Command executed");
        return "/master/master_panel.jsp";
    }
}

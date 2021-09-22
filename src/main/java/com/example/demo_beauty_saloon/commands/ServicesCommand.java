package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.ServiceDao;
import com.example.demo_beauty_saloon.entity.Service;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Services Command. Get all Services
 *
 */
public class ServicesCommand extends Command {

    private static final Logger log = Logger.getLogger(ServicesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        response.setContentType("text/html;charset=utf-8");
        ServiceDao my = new ServiceDao();
        List<Service> col = my.selectAllServices();
        request.setAttribute("col", col);
        log.trace("Set the request attribute: col --> " + col);
        log.debug("Command executed");
        return "/services.jsp";
    }
}

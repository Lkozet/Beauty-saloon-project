package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.MasterDao;
import com.example.demo_beauty_saloon.entity.Master;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Master Command. Get all Masters
 *
 */
public class MasterCommand extends Command {

    private static final Logger log = Logger.getLogger(MasterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        response.setContentType("text/html;charset=utf-8");
        MasterDao my = new MasterDao();
        List<Master> col2 = my.selectAllMasters();
        request.setAttribute("col2", col2);
        log.trace("Set the request attribute: col2 --> " + col2);
        log.debug("Command executed");
        return "/masters.jsp";
    }
}

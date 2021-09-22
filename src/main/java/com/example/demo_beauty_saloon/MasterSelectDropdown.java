package com.example.demo_beauty_saloon;

import com.example.demo_beauty_saloon.dao.MasterDao;
import com.example.demo_beauty_saloon.dao.ServiceDao;
import com.example.demo_beauty_saloon.entity.Master;
import com.example.demo_beauty_saloon.entity.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Display services in <select id="services"></select> on jsp load
 * and display masters in <select id="masters"></select> on service change
 *
 */
public class MasterSelectDropdown extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {
            String op = request.getParameter("operation");
            if (op.equals("services")) {
                ServiceDao masterDao = new ServiceDao();
                List<Service> mlist = masterDao.selectAllServices();
                Gson json = new Gson();
                String mastersList = json.toJson(mlist);
                response.setContentType("text/html");
                response.getWriter().write(mastersList);
            }
            if (op.equals("masters")) {
                MasterDao masterDao = new MasterDao();
                int id = Integer.parseInt(request.getParameter("id"));
                List<Master> mlist = masterDao.selectMastersByServiceId(id);
                Gson json = new Gson();
                String mastersList = json.toJson(mlist);
                response.setContentType("text/html");
                response.getWriter().write(mastersList);
            }
        }
    }
}

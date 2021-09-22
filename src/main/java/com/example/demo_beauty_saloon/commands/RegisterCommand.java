package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.dao.UserDao;
import com.example.demo_beauty_saloon.entity.User;
import com.example.demo_beauty_saloon.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Register Command. Register User with given parameters (if valid)
 *
 */
public class RegisterCommand extends Command {

    private static final Logger log = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        UserDao userDao = new UserDao();
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User client = new User();
        Validator validator = new Validator();
        client.setName(name);
        client.setPassword(password);
        client.setEmail(email);
        client.setPhone(phone);
        log.trace(client);

        try {
            if (validator.isNameValid(name) && validator.isEmailValid(email)
                    && validator.isPasswordValid(password) && validator.isPhoneNumberValid(phone)) {
                if (userDao.registerUser(client) > 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userAlreadyExists", "");
                    log.debug("Command executed");
                    return "/index.jsp";
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("userAlreadyExists", "Пользователь уже существует");
                    log.debug("Command executed");
                    return "/register.jsp";
                }
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("userAlreadyExists", "Данные введены не верно");
                log.debug("Command executed");
                return "/register.jsp";
            }
        } catch (Exception e) {
            log.error(e);
            return "/errors/error-500.jsp";
        }
    }
}

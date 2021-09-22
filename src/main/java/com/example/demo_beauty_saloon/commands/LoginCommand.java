package com.example.demo_beauty_saloon.commands;

import com.example.demo_beauty_saloon.bean.LoginBean;
import com.example.demo_beauty_saloon.dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Login command. Checks login and password
 *
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger log = Logger.getLogger(LoginCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		log.debug("Command starts");

		// obtain login and password from the request
		String username = request.getParameter("username");
		log.trace("Request parameter: username --> " + username);
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		if (userDao.validate(loginBean)) {
			log.trace("Found in DB: user --> " + username);
			String role = userDao.getUserRole(loginBean);
			log.trace("userRole --> " + role);
			session.setAttribute("userrole", role);
			log.trace("Set the session attribute: userRole --> " + role);
			session.setAttribute("username", username);
			log.trace("Set the session attribute: user --> " + username);
			log.info("User " + username + " logged as " + role);
			session.setAttribute("hidden", "hidden");
			session.setAttribute("userNotFound", "");
			log.debug("Command finished");
			return "/index.jsp";
		} else {
			if (session.getAttribute("language") == null) {
				session.setAttribute("userNotFound", "Пароль или пользователь введены не верно");
			} else if (session.getAttribute("language").equals("ru")) {
				session.setAttribute("userNotFound", "Пароль или пользователь введены не верно");
			} else {
				session.setAttribute("userNotFound", "Wrong password/login provided");
			}
			log.debug("Command finished");
			return "/login.jsp";
		}
	}

}
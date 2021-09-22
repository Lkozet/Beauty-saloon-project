package com.example.demo_beauty_saloon.commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Logout command. Clears Session and logouts current User
 *
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	private static final Logger log = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("Command starts");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		request.getRequestDispatcher("index.jsp").include(request, response);
		out.print("<div align=\"center\">");
		out.print("<h2>Вы успешно вышли из системы!</h2>");
		out.print("</div>");

		out.close();
		log.debug("Command finished");
		return "/index.jsp";
	}

}
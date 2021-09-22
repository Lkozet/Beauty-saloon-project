package com.example.demo_beauty_saloon.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * No command.
 *
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	private static final Logger log = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("Command starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		log.error("Set the request attribute: errorMessage --> " + errorMessage);

		log.debug("Command executed");
		return "/errors/error-500.jsp";
	}

}
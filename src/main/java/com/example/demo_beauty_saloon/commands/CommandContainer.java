package com.example.demo_beauty_saloon.commands;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.
 *
 */
public class CommandContainer {
	
	private static final Logger log = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("admin", new AdminCommand());
		commands.put("changeLang", new ChangeLangCommand());
		commands.put("deleteApp", new DeleteAppCommand());
		commands.put("editAppTimestamp", new EditAppTimestampCommand());
		commands.put("clientApps", new ClientAppCommand());
		commands.put("masterApps", new MasterAppCommand());
		commands.put("masters", new MasterCommand());
		commands.put("services", new ServicesCommand());
		commands.put("changePaymentStatus", new PaymentStatusCommand());
		commands.put("registerUser", new RegisterCommand());
		commands.put("registerApp", new RegisterAppCommand());
		commands.put("updateComment", new UpdateCommentCommand());
		commands.put("updateStatus", new AppStatusCommand());
		
		log.debug("Command container was successfully initialized");
		log.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			log.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}
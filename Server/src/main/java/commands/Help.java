package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

import java.util.HashMap;

public class Help implements Command {

	private final Receiver receiver;
	HashMap<String, Command> commandMap = new HashMap<>();

	public Help(Receiver receiver, HashMap<String, Command> commandMap) {
		this.receiver = receiver;
		this.commandMap = commandMap;
	}

	@Override
	public Response execute(Request request) {
		String[] commandshelp = commandMap.values().stream()
				.map(Command::getHelp)
				.toArray(String[]::new);
		Response reaction = new Response();
		reaction.setAnswer(commandshelp);
		return reaction;
	}

	@Override
	public String getHelp() {
		return "Введите help, если хотитите вывести справку по доступным командам";
	}
}

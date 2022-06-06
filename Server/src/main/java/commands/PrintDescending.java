package commands;

import listening.Request;
import server.Receiver;

public class PrintDescending implements Command {

	private final Receiver receiver;

	public PrintDescending(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public void execute(Request request) {

	}

	@Override
	public String getHelp() {
		return null;
	}
}

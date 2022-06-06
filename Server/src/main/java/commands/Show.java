package commands;

import listening.Request;
import server.Receiver;

public class Show implements Command {

	private final Receiver receiver;

	public Show(Receiver receiver) {
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

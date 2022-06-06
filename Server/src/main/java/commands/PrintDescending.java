package commands;

import server.Receiver;

public class PrintDescending implements Command {

	private final Receiver receiver;

	public PrintDescending(Receiver receiver) {
		this.receiver = receiver;

	}

}

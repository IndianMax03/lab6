package commands;

import server.Receiver;

public class Exit implements Command {

	private final Receiver receiver;

	public Exit(Receiver receiver) {
		this.receiver = receiver;

	}
}

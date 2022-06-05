package commands;

import server.Receiver;

public class Show implements Command {

	private final Receiver receiver;

	public Show(Receiver receiver) {
		this.receiver = receiver;

	}

}

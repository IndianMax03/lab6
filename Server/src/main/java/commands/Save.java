package commands;

import server.Receiver;

public class Save implements Command {

	private final Receiver receiver;

	public Save(Receiver receiver) {
		this.receiver = receiver;

	}

}

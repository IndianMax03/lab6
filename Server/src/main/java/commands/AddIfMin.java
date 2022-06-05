package commands;

import server.Receiver;

public class AddIfMin implements Command {
	private final Receiver receiver;

	public AddIfMin(Receiver receiver) {
		this.receiver = receiver;

	}
}

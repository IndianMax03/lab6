package commands;

import server.Receiver;

public class Info implements Command {

	private final Receiver receiver;

	public Info(Receiver receiver) {
		this.receiver = receiver;

	}

}

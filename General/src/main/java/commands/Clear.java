package commands;

import server.Receiver;

public class Clear implements Command{
	private final Receiver receiver;

	public Clear(Receiver receiver) {
		this.receiver = receiver;

	}
}

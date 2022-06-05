package commands;

import server.Receiver;

public class UpdateId implements Command {

	private final Receiver receiver;

	public UpdateId(Receiver receiver) {
		this.receiver = receiver;

	}

}

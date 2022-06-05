package commands;

import server.Receiver;

public class RemoveGreater implements Command {

	private final Receiver receiver;

	public RemoveGreater(Receiver receiver) {
		this.receiver = receiver;

	}

}

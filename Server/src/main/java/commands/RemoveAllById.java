package commands;

import server.Receiver;

public class RemoveAllById implements Command {

	private final Receiver receiver;

	public RemoveAllById(Receiver receiver) {
		this.receiver = receiver;

	}

}

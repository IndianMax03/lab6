package commands;

import server.Receiver;

public class RemoveLower implements Command {

	private final Receiver receiver;

	public RemoveLower(Receiver receiver) {
		this.receiver = receiver;

	}

}

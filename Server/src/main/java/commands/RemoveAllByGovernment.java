package commands;

import server.Receiver;

public class RemoveAllByGovernment implements Command {

	private final Receiver receiver;

	public RemoveAllByGovernment(Receiver receiver) {
		this.receiver = receiver;

	}

}

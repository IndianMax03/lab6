package commands;

import server.Receiver;

public class ExecuteScript implements Command{

	private final Receiver receiver;

	public ExecuteScript(Receiver receiver) {
		this.receiver = receiver;

	}
}

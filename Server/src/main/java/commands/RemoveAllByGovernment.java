package commands;

import listening.Request;
import server.Receiver;

public class RemoveAllByGovernment implements Command {

	private final Receiver receiver;

	public RemoveAllByGovernment(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public void execute(Request request) {

	}

	@Override
	public String getHelp() {
		return null;
	}
}

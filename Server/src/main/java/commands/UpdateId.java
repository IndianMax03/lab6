package commands;

import listening.Request;
import server.Receiver;

public class UpdateId implements Command {

	private final Receiver receiver;

	public UpdateId(Receiver receiver) {
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

package commands;

import server.Receiver;

public class Help implements Command{

	private final Receiver receiver;

	public Help(Receiver receiver) {
		this.receiver = receiver;

	}

}

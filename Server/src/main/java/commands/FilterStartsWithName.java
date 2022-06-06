package commands;

import server.Receiver;

public class FilterStartsWithName implements Command {

	private final Receiver receiver;

	public FilterStartsWithName(Receiver receiver) {
		this.receiver = receiver;

	}

}

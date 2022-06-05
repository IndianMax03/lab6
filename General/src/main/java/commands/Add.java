package commands;

import server.Receiver;

public class Add implements Command{

	private final Receiver receiver;

	public Add(Receiver receiver){
		this.receiver = receiver;


	}
}

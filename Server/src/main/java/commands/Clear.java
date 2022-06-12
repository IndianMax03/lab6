package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class Clear implements Command {
	private final Receiver receiver;

	public Clear(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		receiver.clearResponse();
		return receiver.clear();
	}

	@Override
	public String getHelp() {
		return "Введите clear, чтобы очистить коллекцию";
	}
}

package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class RemoveGreater implements Command {

	private final Receiver receiver;

	public RemoveGreater(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		receiver.clearResponse();
		return receiver.removeGreater(request.getCity());
	}

	@Override
	public String getHelp() {
		return "Введите remove_greater {element}, чтобы удалить из коллекции все элементы, превышающие заданный";
	}
}

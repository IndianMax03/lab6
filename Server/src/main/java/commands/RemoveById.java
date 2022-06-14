package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class RemoveById implements Command {

	private final Receiver receiver;

	public RemoveById(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		receiver.clearResponse();
		return receiver.removeById(request.getArgument());
	}

	@Override
	public String getHelp() {
		return "Введите remove_by_id id, чтобы удалить элемент из коллекции по его id";
	}
}

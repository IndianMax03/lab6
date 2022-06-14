package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class UpdateId implements Command {

	private final Receiver receiver;

	public UpdateId(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		receiver.clearResponse();
		return receiver.updateId(request.getArgument(), request.getCity());
	}

	@Override
	public String getHelp() {
		return "Введите update_id {element}, чтобы обновить значение элемента коллекции, id которого равен заданному";
	}
}

package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class RemoveAllByGovernment implements Command {

	private final Receiver receiver;

	public RemoveAllByGovernment(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		receiver.clearResponse();
		return receiver.removeAllByGovernment(request.getArgument());
	}

	@Override
	public String getHelp() {
		return "Введите remove_all_by_government government, чтобы удалить из коллекции все элементы, значение поля" +
				" government которого эквивалентно заданному";
	}
}

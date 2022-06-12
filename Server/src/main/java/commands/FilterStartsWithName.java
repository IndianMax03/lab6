package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class FilterStartsWithName implements Command {

	private final Receiver receiver;

	public FilterStartsWithName(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		receiver.clearResponse();
		return receiver.filterStartsWithName(request.getArgument());
	}

	@Override
	public String getHelp() {
		return "Введите filter_starts_with_name name, чтобы вывести элементы, значение поля name которых начинается с " +
				"заданной подстроки";
	}
}

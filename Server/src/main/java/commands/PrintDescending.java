package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class PrintDescending implements Command {

	private final Receiver receiver;

	public PrintDescending(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		receiver.clearResponse();
		return receiver.printDescending();
	}

	@Override
	public String getHelp() {
		return "Введите print_descending, чтобы вывести элементы коллекции в порядке убывания";
	}
}

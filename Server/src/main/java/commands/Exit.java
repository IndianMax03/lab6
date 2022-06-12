package commands;

import listening.Request;
import listening.Response;
import server.Receiver;

public class Exit implements Command {

	private final Receiver receiver;

	public Exit(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public Response execute(Request request) {
		Response response = new Response();
		response.setMessage("Пока без Exit"); //  todo
		return response;
	}

	@Override
	public String getHelp() {
		return "Введите exit, чтобы завершить программу (без сохранения в файл)";
	}
}

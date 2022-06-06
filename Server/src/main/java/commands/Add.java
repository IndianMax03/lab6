package commands;

import listening.Request;
import server.Receiver;

public class Add implements Command {

	private final Receiver receiver;

	public Add(Receiver receiver){
		this.receiver = receiver;
	}


	@Override
	public void execute(Request request) {
		// todo Остановился на реализации команд. Иду в ресивер
	}

	@Override
	public String getHelp() {
		return "Введите add {element}, чтобы добавить новый элемент в коллекцию";
	}
}

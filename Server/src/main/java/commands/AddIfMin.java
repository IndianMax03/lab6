package commands;

import listening.Request;
import server.Receiver;

public class AddIfMin implements Command {

	private final Receiver receiver;

	public AddIfMin(Receiver receiver) {
		this.receiver = receiver;

	}

	@Override
	public void execute(Request request) {
		return;
	}

	@Override
	public String getHelp() {
		return "Введите add_if_min {element}, чтобы добавить новый элемент в коллекцию, если его значение меньше, чем у" +
				" наименьшего элемента этой коллекции";
	}
}

package commands;

import listening.Request;

public interface Command {

	void execute(Request request);

	String getHelp();

}

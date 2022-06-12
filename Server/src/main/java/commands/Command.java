package commands;

import listening.Request;
import listening.Response;

public interface Command {

	Response execute(Request request);

	String getHelp();

}

package server;

import base.City;
import listening.Request;
import listening.Response;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	private static final Receiver receiver = new Receiver();
	private static final Invoker invoker = new Invoker(receiver);
	public static void main(String[] args) {
		Server server = new Server();

		while (true){
			Request request = server.recieve();
			if (request == null){
				continue;
			}
			Response response = invoker.execute(request);
			server.send(response, request.getClientAdress());
		}

	}
}

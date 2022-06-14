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
	public static void main(String[] args) throws IOException {
		Server server = new Server();

		while (true) {
			if (System.in.available() > 0) {
				String servcomment;
				try {
					servcomment = (new Scanner(System.in)).nextLine();
				} catch (NullPointerException e) {
					return;
				}
				if (servcomment.equals("save")) {
					Request request = new Request("save", null);
					Response response = invoker.getCommandMap().get("save").execute(request);
					System.out.println("Коллекция записана в файл.");
				} else if (servcomment.equals("exit")) {
					System.out.println("Сервер завершает свою работу.");
					System.exit(0);
				} else {
					System.out.println("Странные игры. Хватит.");
				}
			}

			Request request = server.recieve();
			if (request == null) {
				continue;
			}
			Response response = invoker.execute(request);
			server.send(response, request.getClientAdress());
		}

	}
}

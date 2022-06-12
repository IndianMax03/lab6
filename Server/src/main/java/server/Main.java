package server;

import base.City;
import listening.Request;
import listening.Response;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		Server server = new Server();

		while (true){
			Request request = server.recieve();
			if (request == null){
				continue;
			}
			System.out.println("Клиент прислал команду: " + request.getCommandName());
			Response response = new Response();
			response.setMessage("Кто прочитал тот лох");
			server.send(response, request.getClientAdress());
		}

	}
}

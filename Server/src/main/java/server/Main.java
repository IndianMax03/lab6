package server;

import base.City;
import listening.Request;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		TreeSet<City> collection = new TreeSet<>();
		Invoker invoker = new Invoker(args[0], new Receiver());
		invoker.execute(new Request("fill", args));
		System.out.println("> ");

		while (true){
			Request request = Server.shipToClient();
			if (request != null){
				invoker.execute(request);
			}

			try {
				if (System.in.available() > 0) {
					System.out.println("Я не понимаю, что происходит ниже: ");
					invoker.execute(new Request(new Scanner(System.in).nextLine(), new String[] {}));
					System.out.print("> ");
				}
			} catch (IOException e) {
				System.out.println("Ошибка ввода.");
			} catch (ArrayIndexOutOfBoundsException ex){
				System.out.println("Файл не указан.");
			}
		}
	}
}

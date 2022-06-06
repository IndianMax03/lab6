package client;

import base.City;
import listening.Request;
import listening.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Client {

	private static final int PORT = 666;
	private static final int BUF_SIZE = 32768;
	private static DatagramSocket socket;
	private static DatagramPacket packet;
	private static InetAddress host;
	private static final String hostName = null;

	public Client() { //  Установка соединения с сервером
		try {
			host = InetAddress.getByName(hostName);
			socket = new DatagramSocket();
			return;
		} catch (SocketException e) {
			System.out.println("Сокет не может быть создан.");
			;
		} catch (UnknownHostException e) {
			System.out.println("Сокет не найден.");
			;
		}
		System.out.println("Прекращаю попытку соединения..");
		System.exit(1);
	}

	public static Response shipFromServer() { //  Получение ответа от сервера
		try {
			ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
			packet = new DatagramPacket(buffer.array(), buffer.array().length);
			ObjectInputStream ois = new ObjectInputStream(
					new ByteArrayInputStream(buffer.array())
			);
			return (Response) ois.readObject();
		} catch (IOException ex) {
			System.out.println("Пакет не был получен.");
		} catch (ClassNotFoundException e) {
			System.out.println("Получен неверный ответ от сервера.");
		}
		return null;
	}

	public static void sendSingleCommandToServer(String line){
		Client.sendCommandToServer(line, null);
	}

	/**
	 * Метод создаёт запрос в виде отсылаемой серверу команды
	 * Перед отправкой запрос упаковывается в массив байтов, в котором лежит имя команды, массив аргументов и объект
	 * Получая exit, завершает работу программы
	 */
	public static void sendCommandToServer(String line, City city) {

		try {

			while (line.contains("  ")) {
				line = line.replace("  ", " ");
			}
			String[] commandLine = line.trim().split(" ");
			String command = commandLine[0].trim();

			if (command.equals("exit")) {
				System.out.println("Спасибо за работу, до свидания!");
				System.exit(1);
			}

			Request request = new Request(command, Arrays.copyOfRange(commandLine, 1, commandLine.length), city);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(request);
			//  todo baos.size может дать ошибку, возможно, стоит поменять на baos.toByteArray().length
			packet = new DatagramPacket(baos.toByteArray(), baos.size(), host, PORT);
		} catch (IOException ex){
			System.out.println("Команда не может быть отправлена на сервер.");
		}
	}

}

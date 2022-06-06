package server;

import listening.Request;
import listening.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {

	private static final int PORT = 666;
	private static final int BUF_SIZE = 32768;
	private static DatagramChannel channel;

	static { //  Статический блок, вызывается при инициализации класса
		try{
			InetSocketAddress address = new InetSocketAddress(PORT);
			channel = DatagramChannel.open();
			channel.configureBlocking(false); //  Неблокирующий режим
			System.out.println("Сервер начал свою работу.");
		} catch (IOException ex){
			System.out.println("Сервер не может быть запущен.");
		}
	}

	public static Request shipToClient(){
		try{
			ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
			SocketAddress address = channel.receive(buffer);
			if (address != null){
				System.out.println("Сервер получил запрос на подключение от клиента: " + address);
				ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
				Request request = (Request) ois.readObject();
				request.client = address;
				return request;
			}
		} catch (IOException ex) {
			System.out.println("Сервером получена невалидная информация.");
		} catch (ClassNotFoundException ex){
			System.out.println("Не удалось восстановить объект.");
		}
		return null;
	}

	public static void swim(Response response, SocketAddress address){
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos))
		{
			oos.writeObject(response); //  Сериализуем response
			channel.send(ByteBuffer.wrap(baos.toByteArray()), address); //  Отправка буфера на клиент
			System.out.println("Сервер отправил ответ на клиент: " + address);
		} catch (IOException e) {
			System.out.println("Ответ от сервера не может быть отправлен на клиент: " + address);
		}
	}

	public static void askAnObject(SocketAddress address, Class<? extends Serializable> clazz){
		Response response = new Response();
		response.out = "swim";
		response.clazz = clazz;

		Server.swim(response, address);
	}

	public static void talk(SocketAddress adress, String phrase){ //  Разговор

		if (adress == null){ //  Разговор с самим собой
			System.out.println(phrase);
			return;
		}

		Response response = new Response();
		response.out = phrase;
		Server.swim(response, adress); //  Разговор с клиентом
	}

}

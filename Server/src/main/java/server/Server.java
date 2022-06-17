package server;

import fileworker.WorkWithFile;
import listening.Request;
import listening.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {

	private static final int PORT = 9000;
	private static final int BUF_SIZE = 32768;
	private static DatagramChannel channel;

	static { //  Статический блок, вызывается при инициализации класса
		try{
			channel = DatagramChannel.open(); //  Открыли канал
			channel.bind(new InetSocketAddress(PORT)); //  Привязали канал к порту
			channel.configureBlocking(false); //  Неблокирующий режим
			System.out.println("Сервер начал свою работу.");
		} catch (IOException ex){
			System.out.println("Сервер не может быть запущен.");
		}
	}

	public Request recieve(){
		try {
			ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE); //  Буфер, в который пишется входной поток байтов
			SocketAddress clientAddress = channel.receive(buffer); //  Адрес возврата

			if (clientAddress == null){
				return null;
			}

			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.array())); //  Для десереализации
			Request request = (Request) ois.readObject();
			request.setClientAdress(clientAddress);

			return request;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void send(Response response, SocketAddress clientAdress){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{

			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(response);
			channel.send(ByteBuffer.wrap(baos.toByteArray()), clientAdress);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

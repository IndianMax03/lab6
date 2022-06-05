
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

//  Socket - отправляет данные, Packet - упаковывает данные
public class ClientConnection {
	public static void main(String[] args) throws IOException {

		String str = "Maxim";
		byte[] arr = str.getBytes(StandardCharsets.UTF_8);
		System.out.println(Arrays.toString(arr));
		DatagramChannel dc; //  Представляет открытое соединение с сетевым сокетом (UDP/IP)
		ByteBuffer buffer; //  Байтовый  буфер, который содержит информацию, передаваемую на сервер
		InetAddress host = InetAddress.getLocalHost(); //  Получает интернет-адрес локально
		int port = 112; //  данные о порте присоединения
		SocketAddress address; //

		address = new InetSocketAddress(host, port); //  Совмещает и IP адрес, и порт
		dc = DatagramChannel.open(); //  Открываем канал для связи

		buffer = ByteBuffer.wrap(arr); //  В буфере находится массив байтов
		dc.send(buffer, address); //  Отправляем буфер и адрес возврата в открытый(21) канал

		buffer.clear();
		address = dc.receive(buffer); //  Клиент ждет данные с сервера (завис)

		buffer.flip();
		StringBuilder get = new StringBuilder();
		while(buffer.hasRemaining()){
			get.append((char) buffer.get());
		}

		System.out.println("Сервер вернул: " + get);

	}
}
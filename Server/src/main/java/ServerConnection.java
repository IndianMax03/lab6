import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class ServerConnection {
	public static void main(String[] args) throws IOException {

		byte[] array = new byte[10];
		DatagramChannel dc;
		ByteBuffer buffer;
		int port = 112;
		SocketAddress address;

		address = new InetSocketAddress(port);
		dc = DatagramChannel.open(); //  Открываем канал связи
		dc.bind(address); //  Привязываем канал к порту

		buffer = ByteBuffer.wrap(array);
		address = dc.receive(buffer); //  Получаем адрес возврата

		buffer.flip();
		StringBuilder get = new StringBuilder();
		while(buffer.hasRemaining()){
			get.append((char) buffer.get());
		}

		System.out.println("От клиента пришло: " + get);

		buffer.position(3);
		buffer.put("on".getBytes(StandardCharsets.UTF_8));

		buffer.flip(); //  Готовим буфер к тому, чтобы из него читали при получении
		dc.send(buffer, address);

	}
}

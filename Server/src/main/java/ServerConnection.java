import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerConnection {
	public static void main(String[] args) throws IOException {

		byte[] array = new byte[10];
		int len = array.length;
		DatagramChannel dc;
		ByteBuffer buffer;
		int port = 112;
		SocketAddress address;

		address = new InetSocketAddress(port);
		dc = DatagramChannel.open();
		dc.bind(address);

		buffer = ByteBuffer.wrap(array);
		address = dc.receive(buffer);

		for (int j = 0; j < len; j++){
			array[j] *= 2;
		}

		buffer.flip();
		dc.send(buffer, address);

	}
}

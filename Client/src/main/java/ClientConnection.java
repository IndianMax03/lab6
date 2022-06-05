//

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientConnection {
	public static void main(String[] args) throws IOException {
		byte[] array = {0,1,2,3,4,5,6,7,8,9};
		int len = array.length;
		DatagramChannel dc;
		ByteBuffer buffer;
		InetAddress host = InetAddress.getLocalHost();
		int port = 112;
		SocketAddress address;

		address = new InetSocketAddress(host, port);
		dc = DatagramChannel.open();

		buffer = ByteBuffer.wrap(array);
		dc.send(buffer, address);

		buffer.clear();
		address = dc.receive(buffer);

		for (byte j : array){
			System.out.println(j);
		}
	}
}
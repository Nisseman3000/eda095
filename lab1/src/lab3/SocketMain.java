package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class SocketMain {
	public static void main(String[] args) {
		try {
			ServerSocket socket = new ServerSocket(30000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

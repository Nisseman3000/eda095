package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {
	public static void main(String[] args) {
		try {
			ServerSocket sSocket = new ServerSocket(30000);
			boolean go = true;
			while(go){
				Socket socket = sSocket.accept();
				System.out.println(socket.getInetAddress());
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				int ch = is.read();
				String response  = "Hej\r\n";
				while(ch != -1){
					System.out.print((char) ch);
					ch = is.read();
					if((char) ch == '\n'){
						os.write(response.getBytes());
					}
				}
				socket.close();
			}
			sSocket.close();
			System.out.println("TERMINATED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

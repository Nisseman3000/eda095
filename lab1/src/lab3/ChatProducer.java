package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ChatProducer extends Thread {


	private Socket socket;
	private ChatBox cb;

	

	public ChatProducer(Socket socket, ChatBox cb) {
		super();
		this.socket = socket;
		this.cb = cb;
	}
	
	
	public void run() {

		try {
			System.out.println(socket.getInetAddress() + " connected");
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();

			
			while (line != null) {
				if (line.startsWith("M:")){
					cb.set(line.substring(2).trim());
				}else if (line.startsWith("E:")){
					String message = line.substring(2).trim();
					System.out.println("Echoed " + message + " to " + socket.getInetAddress());
					os.write(message.getBytes());
					os.write('\n');
					os.flush();
				}else if (line.startsWith("Q:")){
					break;
				}

				
				line = br.readLine();
			}
			System.out.println(socket.getInetAddress() + " disconnected");
			socket.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
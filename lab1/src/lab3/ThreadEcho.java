package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import lab2.thread.ThreadMain;

public class ThreadEcho extends Thread{
	Socket socket;

	public ThreadEcho(Socket socket) {
		super();
		this.socket = socket;
	}

	
	public void run() {

		try {
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
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}

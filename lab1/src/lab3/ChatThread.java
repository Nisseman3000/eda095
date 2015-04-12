package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import lab2.thread.ThreadMain;

public class ChatThread extends Thread{
	Socket socket;

	public ChatThread(Socket socket) {
		super();
		this.socket = socket;
	}

	
	public void run() {

		try {
			System.out.println(socket.getInetAddress());
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			byte[] msg = new byte[2];
			int ch = is.read(msg,0,2);
			//String response  = "Hej\r\n";
			String response;
			while(ch != -1){ 
				String ctrlmsg = new String(msg,0,2);
				switch (ctrlmsg) {
	            case "M:":
	            	response = "To all";
	            	os.write(response.getBytes());
	                break;
	            case "E:":
	            	response = "Echo to oneself";
	            	os.write(response.getBytes());
	                break;
	            case "Q:":
	            	response = "Quit";
	            	os.write(response.getBytes());
	                break;
	            default: 
	                
	                break;
	        }

				
				System.out.print(ctrlmsg);
				ch = is.read();
				if((char) ch == '\n'){
					//os.write(response.getBytes());
				}
			}
			socket.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}

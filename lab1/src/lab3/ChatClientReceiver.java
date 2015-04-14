package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClientReceiver extends Thread{
	private Socket socket;
	
	public ChatClientReceiver(Socket socket){
		super();
		this.socket = socket;
	}
	
	public void run(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

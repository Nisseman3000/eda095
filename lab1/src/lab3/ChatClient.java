package lab3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		try {
//			Socket socket = new Socket(args[0],Integer.parseInt(args[1]));
			Socket socket = new Socket("localhost",30000);
			OutputStream os = socket.getOutputStream();
			new ChatClientReceiver(socket).start();
			Scanner scan = new Scanner(System.in);
			while(!socket.isClosed()){
				String line = scan.nextLine();
				if (!socket.isClosed()){
					os.write(line.getBytes());
					os.write('\n');
					os.flush();					
				}
				if (line.startsWith("Q:")){
					break;
				}
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

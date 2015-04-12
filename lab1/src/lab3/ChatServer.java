package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	private static Vector<ChatParticipant> participants = new Vector<ChatParticipant>();
		public static void main(String[] args) {
			try {
				ServerSocket sSocket = new ServerSocket(30000);
				boolean go = true;
				while(go){
					Socket socket = sSocket.accept();
					participants.addElement(new ChatParticipant(socket));
				}
				sSocket.close();
				System.out.println("TERMINATED");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



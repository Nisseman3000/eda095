package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ChatParticipant {

	private String name;
	private int port;
	private Socket socket;
	private ChatBox cb;

	/*
	 * Om ni läser detta, när man kopplade upp sig mot en klient via telnet och
	 * trådmässigt så fick man ett annat portnummer, vi kanske kan använda det
	 * för att kunna hitta alla?
	 * 
	 * alltså socket.getPort(), så den ger rätt port till varje telnet client.
	 */

	public ChatParticipant(Socket socket, ChatBox cb) {
		super();
		port = socket.getPort();
		this.socket = socket;
		this.cb = cb;
		new ChatProducer(socket,cb).start();
	}
	
	public Socket getSocket(){
		return socket;
	}

	
}

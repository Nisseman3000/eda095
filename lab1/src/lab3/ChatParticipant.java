package lab3;

import java.net.Socket;

public class ChatParticipant  {
	
	private String name;
	private int port;
	private Socket socket;
	/* Om ni läser detta, när man kopplade upp sig mot en klient via telnet och trådmässigt så fick man ett
	 * annat portnummer, vi kanske kan använda det för att kunna hitta alla? 
	 * 
	 * alltså socket.getPort(), så den ger rätt port till varje telnet client. 
	 * 
	 */
	
	public ChatParticipant(Socket socket){
		port = socket.getPort();
		this.socket = socket;
		new ChatThread(socket).start();
	}
	
}

package lab3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

public class ChatConsumer extends Thread {
	private ChatBox cb;
	private Vector<ChatParticipant> participants;

	public ChatConsumer(ChatBox cb, Vector<ChatParticipant> participants) {
		super();
		this.cb = cb;
		this.participants = participants;
	}

	public void run() {
		while (true) {
			String message = cb.get();
			for (ChatParticipant p : participants) {
				Socket socket = p.getSocket();
				if (socket.isClosed()){
					participants.remove(p);
					System.out.println("Client " + socket.getInetAddress() + " had closed");
					return;
				}
				try {
					OutputStream stream = socket.getOutputStream();
					stream.write(message.getBytes());
					stream.write('\n');
					stream.flush();
				} catch (IOException e) {
					System.out.println("Could not write to "
							+ socket.getInetAddress());
				}

			}
		}
	}
}

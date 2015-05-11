package lab5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCTimeServerName extends Thread {

	public void run() {
		MulticastSocket mSocket;
		try {
			mSocket = new MulticastSocket(30002);
			InetAddress ia = InetAddress.getByName("230.230.230.230");
			mSocket.joinGroup(ia);
			while (true) {
				DatagramPacket req = new DatagramPacket(new byte[1024], 0, 1024);
				System.out.println("Before receive");
				mSocket.receive(req);
				System.out.println("After receive");
				String s = new String(req.getData());
				s = s.substring(0, req.getLength());
				
				System.out.println("Req:" + s + ":!");
				System.out.println(s.compareTo("greetings"));
				
				if (s.equals("greetings")) {
					System.out.println("I iFSATS");
					String resString = InetAddress.getLocalHost().getHostName();
					DatagramPacket res = new DatagramPacket(
							resString.getBytes(), resString.getBytes().length,
							req.getAddress(), req.getPort());
					mSocket.send(res);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

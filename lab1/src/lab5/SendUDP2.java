package lab5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SendUDP2{
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(args[1]);

		MulticastSocket mSocket = new MulticastSocket(30001);
		mSocket.setTimeToLive(1);
		InetAddress ia = InetAddress.getByName("230.230.230.230");
		String firstString = "greetings";
		DatagramPacket firstReq = new DatagramPacket(firstString.getBytes(),
				firstString.getBytes().length, ia, 30002);
		mSocket.send(firstReq);
		DatagramPacket firstRes = new DatagramPacket(new byte[1024], 0, 1024);
		mSocket.receive(firstRes);
		String firstResString = new String(firstRes.getData());
		System.out.println(firstResString);
		InetAddress machine = InetAddress.getByName(firstResString);
		DatagramSocket socket = new DatagramSocket(20000);
		String s = "";
		for (int i = 2; i < args.length; i++) {
			s += args[i] + " ";
		}
		System.out.println(s);
		DatagramPacket req = new DatagramPacket(s.getBytes(),
				s.getBytes().length, machine, port);
		DatagramPacket res = new DatagramPacket(new byte[1024], 0, 1024);
		socket.send(req);
		socket.receive(res);
		String response = new String(res.getData());
		System.out.println("Svar: " + response);
	}
}

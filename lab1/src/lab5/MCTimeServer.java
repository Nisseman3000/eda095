package lab5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class MCTimeServer {
	public MCTimeServer() {

	}

	public static Locale getLocale(String text) {
		if (text.equals("e")) {
			return Locale.ENGLISH;
		} else if (text.equals("g")) {
			return Locale.GERMAN;
		} else if (text.equals("f")) {
			return Locale.FRENCH;
		} else if (text.equals("c")) {
			return Locale.CHINESE;
		} else {
			return Locale.getDefault();
		}
	}

	public static int getDateFormat(String text) {
		if (text.equals("s")) {
			return DateFormat.SHORT;
		} else if (text.equals("m")) {
			return DateFormat.MEDIUM;
		} else if (text.equals("l")) {
			return DateFormat.LONG;
		} else if (text.equals("f")) {
			return DateFormat.FULL;
		} else {
			return DateFormat.DEFAULT;
		}
	}

	private static ByteArrayInputStream bais;

	public static String readToken() throws IOException {
		StringBuilder sb = new StringBuilder();

		char c;
		while (!Character.isWhitespace(c = (char) bais.read())) {
			sb.append(c);
		}
		return sb.toString();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		MCTimeServerName sName = new MCTimeServerName();
		sName.start();
		DateFormat df = null;
	    
		DatagramSocket socket = new DatagramSocket(30000);
		while (true) {
			DatagramPacket req = new DatagramPacket(new byte[1024], 0, 1024);
			socket.receive(req);
			bais = new ByteArrayInputStream(req.getData());
			String dataString = new String(req.getData());
			System.out.println(dataString + ", " + req.getLength() + ", "
					+ req.getAddress() + ", " + req.getPort());
			switch (readToken()) {
			case "date":
				df = DateFormat.getDateInstance(getDateFormat(readToken()),
						getLocale(readToken()));
				break;
			case "time":
				df = DateFormat.getTimeInstance(getDateFormat(readToken()),
						getLocale(readToken()));
				break;
			case "datetime":
				df = DateFormat.getDateTimeInstance(getDateFormat(readToken()),
						getDateFormat(readToken()), getLocale(readToken()));
				break;
			case "greetings":
				
				continue;
			default:
				System.err.println("Nej!");
				continue;
			}
			byte[] b = df.format(new Date()).toString().getBytes();
			DatagramPacket out = new DatagramPacket(b, b.length,
					req.getAddress(), req.getPort());
			socket.send(out);
		}
	}
}

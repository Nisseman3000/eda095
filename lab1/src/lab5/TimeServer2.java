package lab5;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServer2 {
	public TimeServer2() {

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
	
	public static String readToken() throws IOException{
		StringBuilder sb = new StringBuilder();
		char c;
		while(!Character.isWhitespace(c=(char)System.in.read())){
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		DateFormat df = null;
		while (true) {
			switch (readToken()) {
			case "date":
				df = DateFormat.getDateInstance(getDateFormat(readToken()), getLocale(readToken()));
				break;
			case "time":
				df = DateFormat.getTimeInstance(getDateFormat(readToken()), getLocale(readToken()));
				break;
			case "datetime":
				df = DateFormat.getDateTimeInstance(getDateFormat(readToken()), getDateFormat(readToken()),
						getLocale(readToken()));
				break;
			default:
				System.err.println("Nej!");
				continue;
			}
			System.out.println(df.format(new Date()));
		}
	}
}

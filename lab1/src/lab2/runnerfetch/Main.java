package lab2.runnerfetch;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	private LinkedList<String> urls;
	private int nextURL;
	private URL myDoc;

	public Main() {
		try {
			myDoc = new URL(
					"http://cs.lth.se/eda095/foerelaesningar/?no_cache=1");
			InputStream is = myDoc.openStream();
			Scanner scan = new Scanner(new InputStreamReader(is));
			ArrayList<String> website = new ArrayList<String>();
			while (scan.hasNext()) {
				website.add(scan.nextLine());
			}
			scan.close();

			urls = new LinkedList<String>();
			nextURL = 0;

			String patternURL = "<a\\s*?.*?\\s*?href=\"(.*?\\.pdf)\"\\s*?.*?\\s*?>(.*?)<\\/a>";
			Pattern pattern = Pattern.compile(patternURL);

			for (String row : website) {
				Matcher matcher = pattern.matcher(row);
				while (matcher.find()) {
					urls.add(matcher.group(1));
				}
			}

			for (int i = 0; i < 10; i++) {
				new Runner(this).run();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized URL getNextURL() throws MalformedURLException {
		if ( !urls.isEmpty()){
		String url = urls.removeFirst();
		return new URL(myDoc,url);		
		}else{
			return null;
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
	}
}

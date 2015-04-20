package lab4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawlThread extends Thread {

	private static final int MAX_PAGES = 100;
	private HashSet<URL> collectedPages;
	private HashSet<String> emails;
	private Elements frames;
	private URL url;

	public crawlThread(URL url, HashSet<URL> collectedPages) {
		super();
		this.url = url;
		this.collectedPages = collectedPages;


	}

	public void run() {
		if (collectedPages.size() <= MAX_PAGES) {
			collectedPages.add(url);
//			url = notVisitedPages.poll();
			String urlString = url.toString();
			Document doc = null;
			boolean pageFound = false;
			while (!pageFound) {
				try {
					doc = Jsoup.connect(urlString).get();
					pageFound = true;
				} catch (IOException e) {
					System.err
							.println("Error: \"" + urlString + "\" not found");
					pageFound = false;
				}
			}
			Elements base = doc.getElementsByTag("base");
			String baseString = base.attr("href");

			String searchString = "a[abs:href^=" + urlString + "]"; // Söker i
																	// eda095:s
																	// grenlänkar
			// String searchString = "a[abs:href^=http]"; //Söker alla länkar
			Elements eLinks = doc.select(searchString); // selectar alla a med
														// href
														// som börjar med "http"

			Elements eFrames = doc.select("frame[src]"); // selectar alla frame
															// med
															// attributet src

			Elements eEmails = doc.select("a[href^=mailto:]"); // selectar alla
																// a
																// med href som
																// börjar med
																// "mailto:"

			for (Element elink : eLinks) {
				String hrefLink = elink.attr("href");
				try {
					URL newUrl = new URL(new URL(baseString), hrefLink);
					addPage(newUrl);
					
					new crawlThread(newUrl, collectedPages).start();
					
				} catch (MalformedURLException e0) {
					e0.printStackTrace();
				}
			}
			for (Element email : eEmails) {
				addEmail(email.attr("href"));
			}


		}
		
	}
	public synchronized void addPage(URL url){
		collectedPages.add(url);
	}
	public synchronized void addEmail(String email){
		emails.add(email);
	}
}

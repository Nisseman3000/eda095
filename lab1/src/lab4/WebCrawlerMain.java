package lab4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawlerMain {
	private static final int MAX_PAGES = 1000;
	private HashSet<URL> collectedPages;
	private LinkedList<URL> notVisitedPages;
	private HashSet<String> emails;

	void crawl() throws IOException {
		notVisitedPages = new LinkedList<URL>();
		collectedPages = new HashSet<URL>();
		emails = new HashSet<String>();
		URL url = new URL("http://cs.lth.se/eda095/");

		crawlPage(url);

		System.out.println("Collected Pages:");
		for (URL s : collectedPages)
			System.out.println(s.toString());
		System.out.println("collectedPages size: " + collectedPages.size());
		
		System.out.println("Collected Emails:");
		for (String s : emails)
			System.out.println(s.toString());
		System.out.println("EmailsSize: " + emails.size());
	}

	public void crawlPage(URL url) {
		notVisitedPages.add(url);
		collectedPages.add(url);
		while (collectedPages.size() < MAX_PAGES && notVisitedPages.size() > 0) {
			String urlString = url.toString();
			Document doc = null;

			try {
				doc = Jsoup.connect(urlString).get();
			} catch (IOException e) {
				System.err.println("Error: \"" + urlString + "\" not found");
				url = notVisitedPages.poll();
				continue;
			}

			//Elements base = doc.getElementsByTag("base");
			//String baseString = base.attr("href");

			System.out.println("collectedPages size: " + collectedPages.size());
			System.out.println("notVisitedpages size: "
					+ notVisitedPages.size());
			System.out.println("EmailsSize: " + emails.size());

			// String searchString = "a[abs:href^=" + urlString + "]"; // Söker
			// i
			// eda095:s
			// grenlänkar
			String searchString = "a[abs:href^=http]"; // Söker alla länkar
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
				String hrefLink = elink.attr("abs:href");
				try {
					// new URL(baseString),
					URL newUrl = new URL(hrefLink);
					if (!collectedPages.contains(newUrl.toString())) {
						collectedPages.add(newUrl);
						notVisitedPages.addLast(newUrl);
					}

				} catch (MalformedURLException e0) {
//					System.out.println("Base: " + baseString + " hrefLink: "
//							+ hrefLink);
				}
			}

			for (Element frame : eFrames){
				try {
					URL newUrl = new URL(frame.attr("abs:src"));
					if (!collectedPages.contains(newUrl.toString())) {
						collectedPages.add(newUrl);
						notVisitedPages.addLast(newUrl);
					}
				} catch (MalformedURLException e) {
					System.err.println("SAD SMILEY FACE = :'-(");
				}
			}
			
			for (Element email : eEmails) {
				emails.add(email.attr("href"));
			}

			url = notVisitedPages.poll();
		}
	}

	public static void main(String[] args) throws IOException {
		WebCrawlerMain wcm = new WebCrawlerMain();
		wcm.crawl();
	}

}
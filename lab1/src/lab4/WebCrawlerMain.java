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
	private static final int MAX_PAGES = 100;
	private HashSet<URL> visitedPages;
	private LinkedList<URL> notVisitedPages;
	private HashSet<String> emails;
	private Elements frames;

	void parse() throws IOException {
		notVisitedPages = new LinkedList<URL>();
		visitedPages = new HashSet<URL>();
		emails = new HashSet<String>();
		URL url = new URL("http://cs.lth.se/eda095/");

		// System.out.println(doc.toString());
		searchPage(url);

		System.out.println("Links:");
		
		for (URL s : visitedPages)
		System.out.println(s.toString());

		System.out.println("Emails:");
		for (String s : emails)
			System.out.println(s.toString());

	}

	public void searchPage(URL url) {
		notVisitedPages.add(url);
		while (notVisitedPages.size() < MAX_PAGES && notVisitedPages.size() > 0) {
			String urlString = url.toString();
			Document doc = null;
			boolean pageFound = false;
			while(!pageFound){
			try {
				doc = Jsoup.connect(urlString).get();
				pageFound = true;
			} catch (IOException e) {
				System.err.println("Error: \"" + urlString + "\" not found");
				pageFound =false;
			}
			}
			Elements base = doc.getElementsByTag("base");
			String baseString = base.attr("href");
			System.out.println("visitedpages.size() : " + visitedPages.size());
			System.out.println("notVisitedpages.size() : "
					+ notVisitedPages.size());
			System.out.println("EmailsSize: " + emails.size());

			String searchString = "a[abs:href^=" + urlString + "]";
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
					boolean exist = false;
					for (URL temp : notVisitedPages) {
						if (temp.toString().equals(newUrl.toString())) {
//							System.out.println("hrefLink: " + hrefLink);
							exist = true;
							break;
						}
					}
					if (!exist) {
						visitedPages.add(newUrl);
						notVisitedPages.addLast(newUrl);
					}
				} catch (MalformedURLException e0) {
					e0.printStackTrace();
				}
			}
			for (Element email : eEmails) {
				emails.add(email.attr("href"));
			}

			visitedPages.add(url);
			url = notVisitedPages.poll();
		}
	}

	public static void main(String[] args) throws IOException {
		WebCrawlerMain wcm = new WebCrawlerMain();
		wcm.parse();
	}

}

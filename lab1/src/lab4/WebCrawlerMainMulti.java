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

public class WebCrawlerMainMulti {
	private static final int MAX_PAGES = 100;
	private HashSet<URL> collectedPages;
	private LinkedList<URL> notVisitedPages;
	private HashSet<String> emails;
	private Elements frames;

	void crawl() throws IOException {
		notVisitedPages = new LinkedList<URL>();
		collectedPages = new HashSet<URL>();
		emails = new HashSet<String>();
		URL url = new URL("http://cs.lth.se/eda095/");

		crawlPage(url);

		System.out.println("Collected Pages:");
		for (URL s : collectedPages)
			System.out.println(s.toString());

		System.out.println("Collected Emails:");
		for (String s : emails)
			System.out.println(s.toString());

	}

	public void crawlPage(URL url) {
		notVisitedPages.add(url);
//		while (collectedPages.size() < MAX_PAGES && notVisitedPages.size() > 0) {
			//Utskrifter
			System.out.println("collectedPages size: " + collectedPages.size());
			System.out.println("notVisitedpages size: " + notVisitedPages.size());
			System.out.println("EmailsSize: " + emails.size());
			
			new crawlThread(url,collectedPages).start();	
			url = notVisitedPages.poll();
			
			
//		}
	}

	public static void main(String[] args) throws IOException {
		WebCrawlerMainMulti wcm = new WebCrawlerMainMulti();
		wcm.crawl();
	}

}

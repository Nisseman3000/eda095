package lab4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Processor implements Runnable {
	private Spider spider;
	private URL url;

	public Processor(Spider spider, URL url) {
		this.spider = spider;
		this.url = url;
	}

	public void run() {
		String urlString = url.toString();
		Document doc = null;

		try {
			doc = Jsoup.connect(urlString).get();
		} catch (IOException e) {
			System.err.println("Error: \"" + urlString + "\" not found");
			return;
		}

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
				URL newUrl = new URL(hrefLink);
				if (!spider.visited(newUrl)) {
					spider.addPage(newUrl);
				}

			} catch (MalformedURLException e0) {
				// System.out.println("Base: " + baseString + " hrefLink: "
				// + hrefLink);
			}
		}

		for (Element frame : eFrames) {
			try {
				URL newUrl = new URL(frame.attr("abs:src"));
				if (!spider.visited(newUrl)) {
					spider.addPage(newUrl);
				}
			} catch (MalformedURLException e) {
				System.err.println("SAD SMILEY FACE = :'-(");
			}
		}

		for (Element email : eEmails) {
			spider.addEmail(email.attr("href"));
		}

	}

}

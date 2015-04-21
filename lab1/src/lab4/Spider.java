package lab4;

import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

public class Spider {
	private static final int MAX_PAGES = 1000;
	private HashSet<URL> collectedPages;
	private HashSet<String> emails;
	private ExecutorService es;

	public Spider(ExecutorService es) {
		collectedPages = new HashSet<URL>();
		emails = new HashSet<String>();
		this.es = es;
	}

	public synchronized boolean visited(URL url) {
		return collectedPages.contains(url);
	}

	public synchronized void addPage(URL url) {
 		if (!es.isShutdown()) {
			if (collectedPages.size() < MAX_PAGES) {
				collectedPages.add(url);
				es.submit(new Processor(this, url));
				System.out.println("Pages: " + collectedPages.size());
			} else {
				es.shutdown();
			}
		}else{
			es.shutdownNow();
		}
	}

	public synchronized void addEmail(String attr) {
		emails.add(attr);
	}

	public synchronized void printAll() {
		System.out.println("Collected Pages:");
		for (URL s : collectedPages)
			System.out.println(s.toString());
		System.out.println("Nbr of pages: " + collectedPages.size());
		System.out.println("Collected Emails:");
		for (String s : emails)
			System.out.println(s.toString());
		System.out.println("Nbr of emails: " + emails.size());

	}


}

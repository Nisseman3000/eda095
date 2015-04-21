package lab4;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		Spider spider;
		try {
			URL url = new URL("http://cs.lth.se/eda095/");
			spider = new Spider(es);
			es.submit(new Processor(spider,url));
			es.awaitTermination(3, TimeUnit.MINUTES);
			spider.printAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}

package lab2.executors;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutorMain {
	public static void main(String[] args) {

		try {
			//URL myDoc = new URL(
				//	"http://cs.lth.se/eda095/foerelaesningar/?no_cache=1");
			URL myDoc = new URL("http://cs.lth.se/eda132-applied-artificial-intelligence/course-material/");
			InputStream is = myDoc.openStream();
			Scanner scan = new Scanner(new InputStreamReader(is));
			ArrayList<String> website = new ArrayList<String>();
			while (scan.hasNext()) {
				website.add(scan.nextLine());
			}
			scan.close();

			ArrayList<String> urls = new ArrayList<String>();
			String patternURL = "<a\\s*?.*?\\s*?href=\"(.*?\\.pdf)\"\\s*?.*?\\s*?>(.*?)<\\/a>";
			Pattern pattern = Pattern.compile(patternURL);

			for (String row : website) {
				Matcher matcher = pattern.matcher(row);
				while (matcher.find()) {
					urls.add(matcher.group(1));
				}
			}
			
			ExecutorService es = Executors.newFixedThreadPool(10); 
			
			for (String s : urls) {
				URL tempURL = new URL(myDoc, s);
				es.submit(new ExecutorRunner(tempURL));
			}
			
			es.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

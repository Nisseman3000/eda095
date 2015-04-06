package lab2.runnerconstructor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {

		try {
			URL myDoc = new URL(
					"http://cs.lth.se/eda095/foerelaesningar/?no_cache=1");
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
			for (String s : urls) {
				URL tempURL = new URL(myDoc, s);
				new Thread(new Runner(tempURL)).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

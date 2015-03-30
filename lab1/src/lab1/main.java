package lab1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
	public static void main(String[] args) {
		System.out.println("Let's download some stuff!");

		try {
			URL myDoc = new URL(
					"http://cs.lth.se/eda095/foerelaesningar/?no_cache=1");
			InputStream is = myDoc.openStream();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					is));
			String line;
			ArrayList<String> urls = new ArrayList<String>();
			while ((line = bReader.readLine()) != null) {
				String patternURL = "<a\\s*?.*?\\s*?href=\"(.*?\\.pdf)\"\\s*?.*?\\s*?>(.*?)</a>";
				Pattern pattern = Pattern.compile(patternURL);
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					urls.add(matcher.group(1));
				}
				
			}
			for (String s : urls) {
				//System.out.println(s);
				URL tempURL = new URL(myDoc,s);
				System.out.println(tempURL);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

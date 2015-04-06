package lab2.runnerconstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {
	private URL url;

	public Runner(URL url) {
		this.url = url;
	}

	
	public void run() {
		Path p = Paths.get(url.getPath());
		String file = p.getFileName().toString();
		System.out.println(file + " started!");
		try {
			InputStream in = url.openStream();
			FileOutputStream fos = new FileOutputStream(new File(file));
			int length = -1;
			byte[] buffer = new byte[1024];// buffer for portion of data from
											// connection
			while ((length = in.read(buffer)) > -1) {
				fos.write(buffer, 0, length);
			}
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println(file + " done!");
	}

}

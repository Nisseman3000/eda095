package lab2.runnable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunnableRunner implements Runnable{
	RunnableMain m;

	public RunnableRunner(RunnableMain m) {
		this.m = m;
	}

	
	public void run() {

		try {
			URL url;
			while ((url = m.getNextURL()) != null){
			Path p = Paths.get(url.getPath());
			String file = p.getFileName().toString();

			System.out.println(file + " started!");
			InputStream in = url.openStream();
			FileOutputStream fos = new FileOutputStream(new File(file));
			int length = -1;
			byte[] buffer = new byte[1024];// buffer for portion of data from
											// connection
			while ((length = in.read(buffer)) > -1) {
				fos.write(buffer, 0, length);
			}
			fos.close();
			System.out.println(file + " done!");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}

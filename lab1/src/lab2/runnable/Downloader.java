package lab2.runnable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Downloader implements Runnable {
	private URL url;
	private String file;

	public Downloader(URL url, String file ){
		this.url = url;
		this.file = file;
	}
	
	@Override
	public void run() {
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
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		System.out.println(file + " done!");
	}

}

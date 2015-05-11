package lab5;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class PrintDate {
	public static void main(String[] args){
		 DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.FRANCE);
		 System.out.println(df.format(new Date()));
		 
	}
}

package activemq.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class DateFormater {
	public static final DateFormat ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	public static final DateFormat GMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
	public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
	
	public static String format(Date date) {
		return format(date, GMT);
	}
	public static String format(long ms, TimeZone timeZone) {
		return format(new Date(ms), timeZone);
	}
	public static String format(Date date, TimeZone timeZone) {
		return format(date, timeZone);
	}
	public static String format(long ms) {
		return format(new Date(ms));
	}
	public static String format(long ms, DateFormat format) {
		return format(new Date(ms), format);
	}
	public static String format(Date date, DateFormat format) {
		return format(date, format, UTC);
	}
	public static String format(Date date, DateFormat format, TimeZone timeZone) {
		DateFormat copy = (DateFormat) format.clone();
		if (timeZone != null) {
			copy.setTimeZone(timeZone);
		}
		return copy.format(date);
	}
	
	public static String formatLocal(long ms, DateFormat format) {
		return format.format(new Date(ms));
	}
	public static String formatLocal(Date date, DateFormat format) {
		return format.format(date);
	}
	public static String formatLocal(long ms) {
		return formatLocal(ms, GMT);
	}
	public static String formatLocal(Date date) {
		return formatLocal(date, GMT);
	}
	
	// Example: 1435329109835
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter timestamp (ms): ");
		
		try {
			long ms = sc.nextLong();
			
			System.out.printf("GMT   : %s%n", format(ms));
			System.out.printf("Local : %s%n", formatLocal(ms));
		} catch (NumberFormatException e) {
			System.out.println("An error has occurred...");
			System.exit(0);
		} finally {
			sc.close();
		}
	}
}

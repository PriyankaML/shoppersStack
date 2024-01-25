package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtils {
	
	/**
	 * this method is used to get system date
	 * @return
	 */
	public String getSystemDate()
	{
		Date dt = new Date();
		String date = dt.toString();
		return date;
	}
	
	/**
	 * this method is used to return the date in format
	 * @return
	 */
	public String getSystemDateFormat()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH-mm-ss");
		Date dt = new Date();
		String date = dt.toString();
		String systemDateFormat = dateFormat.format(date);
		return systemDateFormat;
		
	}
	
	/**
	 * this method is used to return the random number
	 * @return
	 */
	public int getRandomNumber()
	{
		Random ran = new Random();
		int ranNumber = ran.nextInt(100);
		return ranNumber;
	}

}

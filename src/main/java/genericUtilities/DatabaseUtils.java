package genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class DatabaseUtils {
	
	Connection con = null;
	String data=null;
	boolean flag=false;
	
	/**
	 * this method is used to register the driver get coonection to database
	 * @throws Throwable
	 */
	public void connectToDB() throws Throwable
	{
	//register driver
	Driver driver=new Driver();
	DriverManager.registerDriver(driver);
	
	//connection to dataBase
	con = DriverManager.getConnection(IpathConstants.DbPath, IpathConstants.DbUsername, IpathConstants.DbPassword);

	}
	
	/**
	 * this method is used to creating and execute the query
	 * @param query
	 * @param cellNo
	 * @param expectedData
	 * @return
	 * @throws Throwable
	 */
	public String executeQueryandGetData(String query, int cellNo, String expectedData) throws Throwable {
		
		//create statement
		Statement stmt = con.createStatement();
		
		//execute query
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
			
			data = result.getString(cellNo);
			
			if (data.equalsIgnoreCase(expectedData)) {
				flag=true;
				break;
			}
			
			}
		if (flag==true) {
		System.out.println("data verified");
		return expectedData;
			
		} else {
        System.out.println("data not verified");
        return null;
		}	
		}
	
	/**
	 * this method is used to close the database
	 * @throws Throwable
	 */
	public void closeDb() throws Throwable
	{
		//close database
		con.close();
	}
	
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



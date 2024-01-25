package genericUtilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ObjectRepository.homepage;
import ObjectRepository.userLoginLogpage;
import ObjectRepository.userLoginPage;
import objectRepository.Homepage;


public class BaseClass {
	
	//creating object of generic utils(public is made because need to extend baseclass)
	public	FileUtils fLib=new FileUtils();
public JavaUtils jLib=new JavaUtils();
public DatabaseUtils dLib=new DatabaseUtils();
public webdriverUtils wLib=new webdriverUtils();
public WebDriver driver;
Homepage hp=new Homepage(driver);


//public homepage hp = new homepage(driver);
	
	//connect to db
	@BeforeSuite
	public void config_BS() throws Throwable
	{
	dLib.connectToDB();
	Reporter.log("DB connected",true);     //to print the stmt in both console and emailable report
	
	}
	
	//launching the browser
	@BeforeClass
	public void config_BC() throws Throwable
	{
		driver=new ChromeDriver();
		wLib.maximizeWindow(driver);
		driver.get(fLib.readDataFromPropertyFile("url"));
		//String URL =  fLib.readDataFromPropertyFile("url");
//		driver.get(URL);
		//System.out.println("launched the browser");
		wLib.waitForPageLoad(driver, 20);
		Reporter.log("launched the browser", true);
//		
	}
	
	//login to application
	@BeforeMethod
	public void config_BM() throws Throwable
	{
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");

		
		WebElement loglink = hp.getLoginLnk();
		
		wLib.clickOnElement(driver, loglink);
		userLoginPage ulp=new userLoginPage(driver);
		ulp.loginToApplication(USERNAME, PASSWORD);
		Reporter.log("logged in to appl", true);
		
	}
	
	
	//logout from application
	@AfterMethod
	public void config_AM()
	{
		hp.logOutClick();
		Reporter.log("logged out from appl", true);
	}
	
	@AfterClass
	public void config_AC()
	{
		driver.quit();
		Reporter.log("closed the browser", true);
	}
	
	//close the db
	@AfterSuite
	public void config_AS() throws Throwable
	{
		dLib.closeDb();
		Reporter.log("closed the db", true);
	}

}

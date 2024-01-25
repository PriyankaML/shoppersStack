package practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class assertTest {
	
	@Test
	public void test()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://rmgtestingserver/domain/Online_Shopping_Application/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		String expect = "Shopping Portal Home Page";
		String actual = driver.getTitle();
		assertEquals(actual, expect, "condition satisfies");
	}
	
	@Test
	public void demo()
	{
		int a=10;
		System.out.println("hi");
		assertNotNull(a);
		System.out.println("satisfied");
	}
	
	
	@Test
	public void soft()
	{
		int b=6;
		SoftAssert sa=new SoftAssert();
		System.out.println("hello");
		System.out.println("hii");
		sa.assertNull(b);
		
		System.out.println("good");
		System.out.println("morning");
		sa.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package practice;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class amazon {
public static void main(String[] args) throws Throwable {
	
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.amazon.in/");
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone");
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[.='Apple iPhone 13 (128GB) - (Product) RED']")).click();
	Thread.sleep(2000);
	
	Set<String> windows = driver.getWindowHandles();
	Iterator<String> window = windows.iterator();
	while (window.hasNext()) {
		String winId = window.next();
		String Title = driver.switchTo().window(winId).getTitle();
		if (Title.contains("Apple-iPhone-13-")) {
			break;
		}
			
		}
List<WebElement> colours = driver.findElements(By.xpath("//img[@class='imgSwatch']"));
	
	System.out.println("fetched");
	for (WebElement colour : colours) {
		String data = colour.getAttribute("alt");
		System.out.println(data);
	}
	
}
}

package genericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class webdriverUtils extends FileUtils{
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	public void waitForPageLoad(WebDriver driver,int sec)
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
	}
	
	public void waitForElementToBeClickable(WebDriver driver, int sec, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementToBeVisible(WebDriver driver, int sec, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeInVisible(WebDriver driver, int sec, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForexpectedTitle(WebDriver driver, int sec, String expectedTitle)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.titleIs(expectedTitle));
	}
	
	public void select(WebElement element,String expectedText)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(expectedText);
	}
	
	public void select(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void select( String value,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	public void clickOnElement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void doubleClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}
	
	public void doubleClickAction(WebElement element,WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	public void rightclick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	public void mouseHover(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void dragAndDrop(WebDriver driver, WebElement sourceElement,WebElement TargetElement )
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(sourceElement, TargetElement).perform();	
	}
	
	public void dragAndDrop(WebDriver driver,WebElement element, int xOffset, int yOffset)
	{
		Actions act=new Actions(driver);
		act.dragAndDropBy(element, xOffset, yOffset);
	}
	/**
	 * this method will press enter key through actions class
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	
	/**
	 * this method will press and release enter key through robot class
	 * @throws Throwable
	 */
	public void enterKey()throws Throwable
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * this method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * this method will switch the frame based on name or Id
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId )
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * this method will switch the frame based 
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * this method will accept the alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * this method will cancel the alert popup
	 * @param driver
	 */
	public void declineAlert(WebDriver driver)
	{
	driver.switchTo().alert().dismiss();	
	}
	
	public void handlewindows(WebDriver driver, String partialTitle)
	{
		//use getwindowhandles() to capture all the windowIds
		Set<String> windows = driver.getWindowHandles();
		
		//iterate through the windows
		Iterator<String> it = windows.iterator();
		
		//check wheather there is next window
		while (it.hasNext()) {
			
		//capture current window id
		String winId = it.next();
			
		//switch to current window and capture title
		String currentWinTitle = driver.switchTo().window(winId).getTitle();
		
		//check wheather current window is expected
		if (currentWinTitle.contains(partialTitle)) {
			break;
		}
		
		}
		
	}
	
	/**
	 * this method will take screenshot and store it in a screenshot folder
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws Throwable 
	 */
	public String getScreenShot(WebDriver driver, String screenShotName) throws Throwable
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = ".\\screenshot\\"+screenShotName+".jpg";
		File dst = new File(path);
		Files.copy(src, dst);
		return dst.getAbsolutePath();
	}
	
	/**
	 * this method is used to scroll the window by random offset
	 * @param driver
	 */
	public void scrollAction(WebDriver driver, int x, int y)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy("+x+","+y+")", "");
	}
	
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
		
		
	}
	
	public void selectByVisibleText(WebElement element,String text)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
		
	}
	
	
}

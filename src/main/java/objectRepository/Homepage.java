package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	@FindBy(xpath = "//a[@href='login.php']")
	private WebElement loginLnk;
	
	@FindBy(xpath = "//a[@href='logout.php']")
	private WebElement logOutLnk;
	
	
	public WebElement getLogOutLnk() {
		return logOutLnk;
	}

	public Homepage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getLoginLnk() {
		return loginLnk;
	}

	public void loginClick()
	{
		loginLnk.click();
	}
	
	public void logOutClick() {
		logOutLnk.click();
	}
	
}

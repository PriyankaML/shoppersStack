package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
	
	@FindBy(xpath="//input[@id='exampleInputEmail1']")
	private WebElement usernameTxt;
	
	@FindBy(xpath = "//input[@id='exampleInputPassword1']")
	private WebElement passwordTxt;
	
	@FindBy(xpath = "//button[@name='login']")
	private WebElement loginBtn;
	
	public loginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getUsernameTxt() {
		return usernameTxt;
	}

	public WebElement getPasswordTxt() {
		return passwordTxt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//buisness library
	public void login(String USERNAME, String PASSWORD)
	{
		usernameTxt.sendKeys(USERNAME);
		passwordTxt.sendKeys(PASSWORD);
		loginBtn.click();
	}
}

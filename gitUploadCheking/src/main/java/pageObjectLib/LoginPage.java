package pageObjectLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dmsStructure.StructurePart.WebDriverCommonLib;

/*
 *    @Ashish
 */

public class LoginPage extends WebDriverCommonLib {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "entCode")
	@CacheLookup
	WebElement entCode;
	
	@FindBy(id = "entName")
	WebElement entName;
	
	@FindBy(id = "userName")
	@CacheLookup
	WebElement userName;
	
	@FindBy(id = "password")
	@CacheLookup
	WebElement password;
	
	@FindBy(id = "loginbtn")
	WebElement loginbtn;
	
	@FindBy(id = "languageChangeCombo")
	WebElement languageChangeCombo;
	
	public WebElement getEntCode() {
		// entervalue(entcode,entName);
		return entCode;
	}
	
	public void setEntName(String entname) {
		entName.sendKeys(entname);
	}
	
	public void setusername(String username) {
		userName.sendKeys(username);
	}
	
	public void setpassword(String Password) {
		password.sendKeys(Password);
	}
	
	public WebElement clickOnLogin() {
		return loginbtn;
	}
	
	public void setLangauage(String language) {
		languageChangeCombo.sendKeys(language);
	}
	
}

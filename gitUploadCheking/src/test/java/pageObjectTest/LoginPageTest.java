package pageObjectTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dmsStructure.StructurePart.WebDriverCommonLib;
import pageObjectLib.LoginPage;
import resources.BaseClass;

public class LoginPageTest extends BaseClass {
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeBrowser();

	}

	@Test
	public void loginPage() {
		Logger logger = LogManager.getLogger(LoginPageTest.class.getName());

		LoginPage lp = new LoginPage(driver);
		lp.waitforpageload();
		lp.implicitWait();
		logger.info("Welcome to Login Page.");
		// logger.info("Clicked on Login button Without any data. ");
		// WebDriverCommonLib.buttonClick(lp.clickOnLogin());
		
		logger.info("Entering Entity Code...");
		// Highlighting 
		// WebDriverCommonLib.highLightElement(driver, lp.getEntCode());
		lp.getEntCode().sendKeys(prop.getProperty("entCode"));
		
		logger.info("Entity Code is entered. Entity code is "+prop.getProperty("entCode"));
		
		logger.info("Entering Username...");
		lp.setusername(prop.getProperty("entUserName"));
		logger.info("Username is entered. Username is "+prop.getProperty("entUserName"));
		
		logger.info("Entering Password...");
		lp.setpassword(prop.getProperty("entPassword"));
		logger.info("Password is entered.");
		
		logger.info("Clicking on Login...");
		lp.clickOnLogin().click();
		logger.info("Clicked on Login Button.");
		logger.info("Welecome to Login");
		
	}
	
	
}
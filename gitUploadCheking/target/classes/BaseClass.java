package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass  {

	public static WebDriver driver;
	public static Properties prop;
	public static SoftAssert ast;
	public static Logger logger = LogManager.getLogger(BaseClass.class.getName());

	public WebDriver initializeBrowser() throws IOException {

		// Defining Properties file
		prop = new Properties();
		logger.info("loading Properties File");
		FileInputStream fis = new FileInputStream(".\\propertiesFile.properties");
		logger.info("Properties File loaded.");
		prop.load(fis);

		// Defining Browser
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			try {
				// Defining WebDriverManager for chrome.
				logger.info("Connecting to Chrome Browser.");
				WebDriverManager.chromedriver().setup();
				logger.info("Chrome Driver is set now.");
				logger.info("Opening  Chrome Browser");
				driver = new ChromeDriver();
				logger.info("Chrome Browser Opened. Welcome to Chrome Browser.");
			} catch (Exception e) {
				ast.assertTrue(false, "Unable to Open Chrome Browser " + e.getMessage());
			}
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			try {
				// Defining WebDriverManager for Firefox.
				logger.info("Connecting to Firefox Browser.");
				WebDriverManager.firefoxdriver().setup();
				logger.info("Firefox Driver is set now.");
				logger.info("Opening  Firefox Browser");
				driver = new FirefoxDriver();
				logger.info("Firefox Browser Opened. Welcome to Firefox Browser.");
			} catch (Exception e) {
				ast.assertTrue(false, "Unable to Open Firefox Browser " + e.getMessage());
			}
		}
		/*
		 * else if (prop.getProperty("browser").equalsIgnoreCase("ie")) { try { //
		 * Defining WebDriverManager for ie.
		 * logger.info("Connecting to Internet Explorer Browser.");
		 * WebDriverManager.iedriver().setup();
		 * logger.info("Internet Explorer Driver is set now.");
		 * logger.info("Opening  Internet Explorer Browser"); driver = new
		 * InternetExplorerDriver(); logger.
		 * info("Internet Explorer Browser Opened. Welcome to Internet Explorer Browser."
		 * ); } catch (Exception e) { ast.assertTrue(false,
		 * "Unable to Open Internet Explorer Browser " + e.getMessage()); } }
		 */
		else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
			try {
				// Defining WebDriverManager for edge.
				logger.info("Connecting to Edge Browser.");
				WebDriverManager.edgedriver().setup();
				logger.info("Edge Driver is set now.");
				logger.info("Opening  Edge Browser");
				driver = new EdgeDriver();
				logger.info("Edge Browser Opened. Welcome to Edge Browser.");
			} catch (Exception e) {
				ast.assertTrue(false, "Unable to Open Edge Browser " + e.getMessage());
			}
		} else {
			logger.error("Failed to open browser,Please check Details");
			ast.assertTrue(false, "Failed to open browser,Please check Details");
		}
		// Browser setup is done.

		try {
			logger.info("Maximizing Browser");
			driver.manage().window().maximize();
			logger.info("Completed Maximizing Browser");
			logger.info("Connecting to Url");
			driver.navigate().to(prop.getProperty("url"));
			logger.info("Connected to Url " + prop.getProperty("url"));
		} catch (Exception e) {
			logger.error("Unable to Connect to URl " + e.getMessage());
			ast.assertTrue(false, "Unable to Connect to URl " + e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public void getScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C://test//" + result + "screenshot.png"));

	}

	public void closeBrowser() {
		try {
			logger.info("Trying to close Browser");
			driver.close();
			logger.info("Browser Closed Successfully");
		} catch (Exception e) {
			logger.error("Unable to close the browser " + e.getMessage());

		}
	}

	public void teardown() {

		try {
			logger.info("Trying to close All Browser");
			driver.quit();
			logger.info("All Browser Closed Successfully");
			driver = null;
			logger.info("Driver Object is null");

			if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
				Runtime.getRuntime().exec("cmd.exe /c start " + "taskkill /f /im geckodriver.exe");
				logger.info("All FirefoxDriver Process is closed.");
			} else if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				Runtime.getRuntime().exec("cmd.exe /c start " + "taskkill /f /im chromedriver.exe");
				logger.info("All ChromeDriver Process is closed.");
			}
			/*
			 * else if (prop.getProperty("browser").equalsIgnoreCase("ie")) {
			 * Runtime.getRuntime().exec("cmd.exe /c start " +
			 * "taskkill /f /im IEDriverServer.exe");
			 * logger.info("All IEServer Process is closed."); }
			 */
			else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
				Runtime.getRuntime().exec("cmd.exe /c start " + "taskkill /f /im msedgedriver.exe");
				logger.info("All EdgeDriver Process is closed.");
			}

		} catch (Exception e) {

			logger.error("Unable to close the browser " + e.getMessage());
		}
	}
}

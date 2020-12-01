package eg.ext.crossbrowser;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.expedia.co.in/";

	@Parameters({ "Browser" })
	@BeforeTest
	public void beforeTest(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driverWait =  new WebDriverWait(driver,15);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driverWait = new WebDriverWait(driver,15);
		}
//		driver.navigate().to(url);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(baseURl);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@AfterTest
	public void afterTest() {
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("Driver already closed!");
		}
	}

}

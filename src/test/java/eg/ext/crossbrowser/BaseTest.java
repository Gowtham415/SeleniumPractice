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
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.expedia.co.in/";

	@Parameters({ "Browser" })
	@BeforeTest
	public void beforeTest(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
			driver = new ChromeDriver();
			driverWait =  new WebDriverWait(driver,Duration.ofSeconds(15));
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Softwares\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			driverWait =  new WebDriverWait(driver,Duration.ofSeconds(15));
		}
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

package eg.ext.crossbrowser;

import java.time.Duration;
import java.util.EnumMap;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.expedia.co.in/";

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = BrowserBuilder.build(BrowserType.CHROME);
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
	
	
	static class BrowserBuilder{
		private static Supplier<WebDriver> chromeDriver = ()->new ChromeDriver();
		private static Supplier<WebDriver> firefoxDriver = ()->new FirefoxDriver();
		private static Supplier<WebDriver> safariDriver = ()->new SafariDriver();
		
		private static EnumMap<BrowserType, Supplier<WebDriver>> map = new EnumMap<BrowserType, Supplier<WebDriver>>(BrowserType.class);
		
		static {
			map.put(BrowserType.CHROME, chromeDriver);
			map.put(BrowserType.FIREFOX, firefoxDriver);
			map.put(BrowserType.SAFARI, safariDriver);
		}
		
		public static WebDriver build(BrowserType browserType) {
			return map.get(browserType).get();
			
		}
	}

}

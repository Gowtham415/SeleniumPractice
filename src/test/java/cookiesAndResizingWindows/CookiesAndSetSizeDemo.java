package cookiesAndResizingWindows;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;

public class CookiesAndSetSizeDemo {

	WebDriver driver;
	JavascriptExecutor js;
	Actions action;
	private final String chromeDriverPath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";

	@BeforeMethod
	public void beforeMethod() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(chromeOptions);
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	@Test
	public void test1() throws InterruptedException {

		// Cookies
		driver.manage().deleteAllCookies();
		driver.get("https://www.google.com/");
		// Setting the size of the window
		Dimension dimension = new Dimension(800, 800);
		driver.manage().window().setSize(dimension);

		driver.manage().window().maximize();
		driver.navigate().to("https://www.youtube.com/");

		Thread.sleep(4500);
		// Different ways of scrolling
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, 2350)");
		Thread.sleep(2000); 
		//js.executeScript("arguments[0].scrollIntoView();", Element);
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}

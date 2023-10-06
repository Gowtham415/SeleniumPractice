package ActionsAndJSExecutor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutorDemo {
	
	String baseURL ="https://www.amazon.in/";
	protected WebDriver driver;
	JavascriptExecutor javaScriptDriver;
	WebDriverWait driverWait;

	@BeforeMethod
	public void beforeMethod() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
		driver = new ChromeDriver(chromeOptions);
		javaScriptDriver = (JavascriptExecutor)driver;
		driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
		driver.get(baseURL);		
	}

	@Test
	public void JSDemoTest() {
		javaScriptDriver.executeScript("window.scrollBy(0,5000)"); 
		javaScriptDriver.executeScript("window.scrollBy(0,-5000)"); 
		Long height = (Long) javaScriptDriver.executeScript("return document.body.scrollHeight");
		System.out.println("Height of the page : "+height);
		String sText =  javaScriptDriver.executeScript("return document.title;").toString();
		System.out.println(sText);
		String sURL = javaScriptDriver.executeScript("return document.URL;").toString();
		System.out.println(sURL);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='About Us']")));
		javaScriptDriver.executeScript("arguments[0].scrollIntoView(true);", element);
		WebElement searchBox = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));
		javaScriptDriver.executeScript("arguments[0].scrollIntoView(true);", searchBox);
		javaScriptDriver.executeScript("document.getElementById('twotabsearchtextbox').value='iPhone XR 64GB';");
		WebElement serachBtn = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-search']//input[@type='submit']")));
		javaScriptDriver.executeScript("arguments[0].click();",serachBtn);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}

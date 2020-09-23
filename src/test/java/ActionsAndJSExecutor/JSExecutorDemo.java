package ActionsAndJSExecutor;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class JSExecutorDemo {
	
	String baseURL ="https://www.amazon.in/";
	protected WebDriver driver;
	JavascriptExecutor JSdriver;
	WebDriverWait driverWait;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		JSdriver = (JavascriptExecutor)driver;
		driverWait = new WebDriverWait(driver,15);
		driver.manage().window().maximize();
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		driver.get(baseURL);		
	}

	@Test
	public void JSDemoTest() {
		JSdriver.executeScript("window.scrollBy(0,5000)"); 
		JSdriver.executeScript("window.scrollBy(0,-5000)"); 
		String sText =  JSdriver.executeScript("return document.title;").toString();
		System.out.println(sText);
		String sURL = JSdriver.executeScript("return document.URL;").toString();
		System.out.println(sURL);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='About Us']")));
		JSdriver.executeScript("arguments[0].scrollIntoView(true);", element);
		WebElement searchBox = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));
		JSdriver.executeScript("arguments[0].scrollIntoView(true);", searchBox);
		JSdriver.executeScript("document.getElementById('twotabsearchtextbox').value='iPhone XR 64GB';");
		WebElement serachBtn = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-search']//input[@type='submit']")));
		JSdriver.executeScript("arguments[0].click();",serachBtn);
		
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

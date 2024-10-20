package ActionsAndJSExecutor;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

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
		driver = new ChromeDriver();
		JSdriver = (JavascriptExecutor)driver;
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().timeouts().scriptTimeout(Duration.ofMillis(3000));//Script timeOuts
		driver.get(baseURL);		
	}

	@Test
	public void JSDemoTest() throws InterruptedException {
		JSdriver.executeScript("window.scrollBy(0,5000)"); 
		JSdriver.executeScript("window.scrollBy(0,-5000)"); 
		String sText =  JSdriver.executeScript("return document.title;").toString();
		System.out.println(sText);
		String sURL = JSdriver.executeScript("return document.URL;").toString();
		System.out.println(sURL);
		JSdriver.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Connect with Us']")));
		JSdriver.executeScript("arguments[0].scrollIntoView(true);", element);
		WebElement searchBox = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));
		JSdriver.executeScript("arguments[0].scrollIntoView(true);", searchBox);
		JSdriver.executeScript("document.getElementById('twotabsearchtextbox').value='iPhone XR 64GB';");
		WebElement serachBtn = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-search']//input[@type='submit']")));
		JSdriver.executeScript("arguments[0].click();",serachBtn);

		synchronized (driver){
			driver.wait(4000);
		}
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}

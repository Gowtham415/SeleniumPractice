package locatorstrategies;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class LocatorsPractice {
	
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.expedia.co.in/";

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait =  new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void locators() {
		
		
		driver.findElement(By.id("d1-btn")).click();
		driver.findElement(By.cssSelector(".uitk-new-date-picker-month-name.uitk-type-medium"));
		driver.findElement(By.tagName("h2"));
		driver.findElement(By.className("uitk-new-date-picker-month-name"));
		driver.findElement(By.linkText("/Packages"));
		driver.findElement(By.partialLinkText("Pack"));
		driver.findElement(By.xpath("//div[@id='new-date-picker']//div[@class='uitk-new-date-picker-month']//h2[text()='November 2020']"));
			
	}

	@AfterMethod
	public void afterMethod() {
	}

}

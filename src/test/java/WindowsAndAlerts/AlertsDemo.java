package WindowsAndAlerts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class AlertsDemo {
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String alertSite ="http://demo.guru99.com/test/delete_customer.php";
	public Actions action;
	public JavascriptExecutor jsExec;

  @BeforeMethod
  public void beforeMethod() { 
	  	System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
		action = new Actions(driver);
		jsExec = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
  }
	
  @Test
  public void alertDemo1() {
	  driver.get(alertSite);
	  driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='cusid']"))).sendKeys("68798");
	  driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='submit']"))).click();	 	  
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
	  driver.switchTo().alert().accept();// Two alerts came
  }
  
  @AfterMethod
  public void afterMethod() {
  }

}

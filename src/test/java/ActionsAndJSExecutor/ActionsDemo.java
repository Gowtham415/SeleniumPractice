package ActionsAndJSExecutor;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class ActionsDemo {
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.expedia.co.in/";
	public Actions action;
 
  @BeforeMethod
  public void beforeMethod() {
	  	System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
		driverWait =  new WebDriverWait(driver,Duration.ofSeconds(15));
  }
  
  @Test(enabled = false)
  public void actionDemo() {
	  driver.get(baseURl);
	  WebElement sk = driver.findElement(By.id("tab-flight-tab-hp"));
	 action.click(sk).build().perform();
  }
  
  @Test()
  public void senKeysWithActions() throws Exception {
	  driver.get(baseURl);
	  WebElement sk = driver.findElement(By.id("tab-flight-tab-hp"));
	 
	  action.click(sk).build().perform();
	  WebElement origin = driver.findElement(By.id("flight-origin-hp-flight"));
	  Actions builder = new Actions(driver);
	  Action seriesOfActions = builder
	  	.moveToElement(origin)
	  	.click()
	  	.keyDown(origin, Keys.SHIFT)
	  	.sendKeys(origin, "hyderabad")
	  	.keyUp(origin, Keys.SHIFT)
	  	.doubleClick(origin)
	  	.contextClick()
	  	.build();
	  
	  	
	 seriesOfActions.perform() ;
	  
	 driver.findElement(By.xpath("//body")).click();
  }

  @AfterMethod
  public void afterMethod() {
//	  driver.close();
  }

}

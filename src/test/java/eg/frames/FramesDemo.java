package eg.frames;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class FramesDemo {
	
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "http://demo.guru99.com/test/guru99home/";

  @BeforeMethod
  public void beforeMethod() {
	  
	  System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
  }
  
  @Test
  public void frameTest1() {
	  driver.get(baseURl);
	  int size = driver.findElements(By.tagName("iframe")).size();
	  System.out.println("Total number of frames:"+size);
	  for(int i=0;i<size;i++) {
		  driver.switchTo().frame(i);
		  System.out.println("Frame:"+i);
		  driver.switchTo().defaultContent();
	  }
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}

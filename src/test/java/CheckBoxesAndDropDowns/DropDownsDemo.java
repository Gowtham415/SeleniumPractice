package CheckBoxesAndDropDowns;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropDownsDemo {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURL = "http://www.peachpit.com/";
	public Actions action;
	public String practiceTestURl = "https://courses.letskodeit.com/practice";

  @BeforeMethod
  public void setUp() {
	  	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
		driverWait = new WebDriverWait(driver,15);
  }
  
  //With Select
  @Test
  public void dropDownTestSelect() {
	  driver.manage().deleteAllCookies();
	  driver.get(practiceTestURl);
	  WebElement selectElement = driver.findElement(By.id("carselect"));
	  
	  Select options = new Select(selectElement);
	  options.getOptions().stream().map(x->x.getText()).distinct().sorted().forEach(System.out::println);
	  
	  
//	  for(WebElement e:options.getAllSelectedOptions()) {
//		  //System.out.println(e.getAttribute("value"));
//		  if(e.getAttribute("value").equals("bmw")) {
//			 options.selectByValue("bmw");
//			 Assert.assertTrue(true);
//		  }else {
//			  System.out.println("Element is not in the drop down");
//			  Assert.assertTrue(false);
//		  }
//	  }
  }
  
  
  //Without SELECT
  @Test(enabled = false)
  public void dropDowntest1() {
	  driver.get(baseURL);
	  WebElement topics= driver.findElement(By.xpath("//a[text()='Topics']"));
	  action.moveToElement(topics).build().perform();
	  List<WebElement> topicsElements = driver.findElements(By.xpath("//a[text()='Topics']//following-sibling::ul/li/a"));
	  int size = topicsElements.size();
	  for(WebElement e:topicsElements) {
		  System.out.println("Topic name: "+e.getText());
		  if(e.getText().equals("APPLE")){
			  e.click();
			  break;
		  }
	  }
	  String newTitle = driver.getTitle();
	  String expected= "Apple Topics | Peachpit";
	  Assert.assertEquals(newTitle, expected, "Title of new page does not match");
  }
  
  @AfterMethod
  public void tearDown() {
	  driver.close();
  }
}

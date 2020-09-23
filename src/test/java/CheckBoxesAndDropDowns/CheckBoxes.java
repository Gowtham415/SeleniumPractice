package CheckBoxesAndDropDowns;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class CheckBoxes {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.ironspider.ca/forms/checkradio.htm";
	public Actions action;
	public String omegaWatchesURL ="https://www.omegawatches.com/watchfinder?product_collection=3180";

	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
		driverWait =  new WebDriverWait(driver,15);
	}

	@Test(priority = 1,groups = {"SMOKE"})
	public void checkBoxTest() {
		driver.get(baseURl);
		List<WebElement> chkBoxes = driver.findElements(By.xpath("//input[@name='color']"));
		for(WebElement e:chkBoxes) {
			if(!e.isSelected()) {
				e.click();
				System.out.println(e.getAttribute("value")+" is selected");
			}else {
				System.out.println(e.getAttribute("value")+" is already selected");
			}
		}
		
		for(WebElement e:chkBoxes) {
			if(e.isSelected()) {
				if(e.getAttribute("value").equals("blue") || e.getAttribute("value").equals("orange")) {
					e.click();
					System.out.println(e.getAttribute("value")+" is de-selected");
				}
			}
		}
	}
	
	@Test(priority = 1)
	public void checBoxComplex() {
		driver.get(omegaWatchesURL);
		WebElement collectionslist= driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/li[1]/button"));
		collectionslist.click();
		Assert.assertTrue(selectProductCheckBox("Constellation"));
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	private boolean selectProductCheckBox(String productType) {
		WebElement prodtypeChkBox= driver.findElement(By.xpath("//div[starts-with(@id,'filter-option')]//li[1]//span[text()='"+productType+"']//preceding-sibling::span"));

		if(!prodtypeChkBox.isSelected()) {
			prodtypeChkBox.click();
			return true;
		}
		
		return false;
	}
	private boolean deSelectProductCheckBox(String productType) {
		WebElement prodtypeChkBox= driver.findElement(By.xpath("//div[starts-with(@id,'filter-option')]//li[1]//span[text()='"+productType+"']//preceding-sibling::span"));

		if(prodtypeChkBox.isSelected()) {
			prodtypeChkBox.click();
			return true;
		}
		
		return false;
	}

}

package AutoITPractice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AutoItDemo {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "http://estpmman71.us.oracle.com:8001/tpm/tpm-ui?root=home";
	public Actions action;
	public JavascriptExecutor jexecute;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
		jexecute = (JavascriptExecutor)driver;
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@Test(invocationCount = 3)
	public void autoITdemo() {
		driver.get(baseURl);
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_username|input"))).sendKeys("sub1");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_password|input"))).sendKeys("wert66");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign In']//parent::div//parent::button"))).click();
		jexecute.executeScript("window.scrollBy(0,1000)");
		WebElement e = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='8888888888']")));
		action.moveToElement(e).click().build().perform();
		WebElement e1 = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Draw 1 (D01)']")));
		action.moveToElement(e1).click().build().perform();
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Attachments')]"))).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Add...']//ancestor::button"))).click();
		UtilityClass.sleep(2);
		WebElement addDoc = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ojChoiceId_addDocumentContract']")));
		action.moveToElement(addDoc).click().build().perform();
		WebElement e3 = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/div[text()='123 - CPM Sub1 Inc.']")));
		action.moveToElement(e3).click().build().perform();
		UtilityClass.sleep(1);
		action.moveToElement(driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ojChoiceId_addDocumentDraw']")))).click().build().perform();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='oj-listbox-results-addDocumentDraw']//div[text()='1']"))).click();
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[starts-with(@id,'attachmentDescriptionInput')]"))).sendKeys("SampleTest");
		 
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'filePickerInput')]"))).click();
		try {
			Runtime.getRuntime().exec("D:\\Project15\\AutoITScripts\\attachmentsPage.exe");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		UtilityClass.sleep(1);
		action.moveToElement(driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalSaveButton_addDocumentModal\"]/button")))).click().build().perform();
		UtilityClass.sleep(1);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}

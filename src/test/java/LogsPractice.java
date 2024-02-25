import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

public class LogsPractice{
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	protected JavascriptExecutor jsDriver;
	public String baseURl = "https://www.expedia.co.in/";
	private static Logger log = LogManager.getLogger(LogsPractice.class.getName());

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		jsDriver = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get(baseURl);
	}

	@Test(invocationCount = 1)
	public void test() throws InterruptedException {
		PropertyConfigurator.configure("log4j.properties");
		log.info("Providing Destination details");
		driver.findElement(By.xpath("//input[@id='location-field-destination-input']//following-sibling::button"))
				.click();
		driver.findElement(
				By.cssSelector(".uitk-flex-item.uitk-typeahead-toolbar-field.toolbar-content-v1 > div>input"))
				.sendKeys("Hyderabad");
		log.debug("CSS Selector is used");
		driver.findElement(By.xpath("//div[@id='location-field-destination-menu']//ul//li[1]/button")).click();
		driver.findElement(By.xpath("//input[@id='d1']/following-sibling::button[1]")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='5']"))).click();
		log.info("Departure Date is selected");
		driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
		driver.findElement(By.xpath("//input[@id='d2']/following-sibling::button[1]")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='10']"))).click();
		log.info("Arrival Date is selected");
		driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
		driver.findElement(By.xpath("//button[text()='Search']")).click();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}


}


import java.time.Duration;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class LogsPractice{
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	protected JavascriptExecutor jsDriver;
	public String baseURl = "https://www.expedia.co.in/";
	private static Logger logger = LogManager.getLogger(LogsPractice.class);

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
		chromeOptions.addArguments("start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		jsDriver = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get(baseURl);
	}

	@Test(invocationCount = 1)
	public void test() throws InterruptedException {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Providing Destination details");
		driver.findElement(By.xpath("//input[@id='location-field-destination-input']//following-sibling::button"))
				.click();
		driver.findElement(
				By.cssSelector(".uitk-flex-item.uitk-typeahead-toolbar-field.toolbar-content-v1 > div>input"))
				.sendKeys("Hyderabad");
		logger.debug("CSS Selector is used");
		driver.findElement(By.xpath("//div[@id='location-field-destination-menu']//ul//li[1]/button")).click();
		driver.findElement(By.xpath("//input[@id='d1']/following-sibling::button[1]")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='5']"))).click();
		logger.info("Departure Date is selected");
		driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
		driver.findElement(By.xpath("//input[@id='d2']/following-sibling::button[1]")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='10']"))).click();
		logger.info("Arrival Date is selected");
		driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
		driver.findElement(By.xpath("//button[text()='Search']")).click();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}


}

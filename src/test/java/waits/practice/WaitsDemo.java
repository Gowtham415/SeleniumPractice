
package waits.practice;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class WaitsDemo {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.expedia.co.in/";
	Wait<WebDriver> fluentWait;

	@BeforeMethod
	public void beforeMethod() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
		driver = new ChromeDriver(chromeOptions);
		driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test
	public void test() {
		driver.get(baseURl);
		driver.findElement(By.xpath("//input[@id='location-field-destination-input']//following-sibling::button"))
		.click();
		driver.findElement(
				By.cssSelector(".uitk-flex-item.uitk-typeahead-toolbar-field.toolbar-content-v1 > div>input"))
				.sendKeys("Hyderabad");
		driver.findElement(By.xpath("//div[@id='location-field-destination-menu']//ul//li[1]/button")).click();
		driver.findElement(By.xpath("//input[@id='d1']/following-sibling::button[1]")).click();
		waitForElement(driver,"//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='5']").click();
		driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
		driver.findElement(By.xpath("//input[@id='d2']/following-sibling::button[1]")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='5']"))).click();
		driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
		driver.findElement(By.xpath("//button[text()='Search']")).click();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	private WebElement waitForElement(WebDriver driver,final String xpath) {	
		return fluentWait.until(new Function<WebDriver,WebElement>(){					
			
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpath));
			}
		});
	}

}


package ActionsAndJSExecutor;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
/* Tested by Gowtham Epparla
*/
public class ActionsDemo {
	private WebDriver driver;
	private WebDriverWait driverWait;
	private String baseURl = "https://www.expedia.co.in/";
	private Actions action;

	@BeforeMethod
	public void beforeMethod() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		action = new Actions(driver);
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test(enabled = false)
	public void actionDemo() {
		driver.get(baseURl);
		WebElement sk = driver.findElement(By.id("tab-flight-tab-hp"));
	}

	@Test(enabled = false)
	public void senKeysWithActions() throws Exception {
		driver.get(baseURl);
		WebElement sk = driver.findElement(By.id("tab-flight-tab-hp"));
		System.out.println(sk.getLocation().getX());
		action.click(sk).build().perform();
		WebElement origin = driver.findElement(By.id("flight-origin-hp-flight"));
		Actions builder = new Actions(driver);
		Action seriesOfActions = builder.moveToElement(origin).click().keyDown(origin, Keys.SHIFT)
				.sendKeys(origin, "hyderabad").keyUp(origin, Keys.SHIFT).doubleClick(origin).contextClick().build();
		seriesOfActions.perform();
		driver.findElement(By.xpath("//body")).click();
	}

	@Test(invocationCount = 1)
	public void sliderDemo() throws InterruptedException {
		driver.get("https://demoqa.com/slider/");
		WebElement slider = driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='range'])[1]")));
		Actions action = new Actions(driver);
		int xLoc = slider.getLocation().getX();// TO get the Current X axis position
		int width = slider.getSize().getWidth();
		System.out.println("XLoc is : "+xLoc);
		System.out.println("Width is : "+width);
		action.dragAndDropBy(slider, 1000, 0).build().perform();

		Thread.sleep(2000);

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}


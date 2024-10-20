package ActionsAndJSExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
/* Tested by Gowtham Epparla
*/
public class ActionsDemo {
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.expedia.co.in/";
	public Actions action;

	@BeforeMethod
	public void beforeMethod() {
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--headless");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

	@Test
	public void sliderDemo() {
		driver.get("https://demoqa.com/slider/");

		WebElement slider = driverWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='range']")));
		Actions action = new Actions(driver);
		int xLoc = slider.getLocation().getX();// TO get the Current X axis position
		int width = slider.getSize().getWidth();
		System.out.println("X Axis:"+xLoc+"\nWidth:"+width);
//		action.clickAndHold(slider).moveByOffset(width,0);
		action.dragAndDropBy(slider, Integer.parseInt(slider.getAttribute("max")), 0).build().perform();

	}

	@AfterMethod
	public void afterMethod() {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		LocalDateTime currentTime = LocalDateTime.now();
		File screenShotFile = new File(System.getProperty("user.dir")+"/screenshots/screenshot"+currentTime+".png");
		try{
			FileUtils.copyFile(srcFile,screenShotFile);
		} catch (IOException e) {
			System.out.println("Unable to take the screenshot");
			e.printStackTrace();
		}
		driver.close();
	}

}

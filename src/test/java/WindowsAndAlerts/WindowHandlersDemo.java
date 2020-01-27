package WindowsAndAlerts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class WindowHandlersDemo {
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://phptravels.com/demo/";
	public String myBookmarks = "file:///D:/Textura.html";
	
	public Actions action;
	public JavascriptExecutor jsExec;

	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		jsExec = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
	}
	// SwitchTo will work if there existing multiple windows
	// NavitageTo will open the URL in the window

	@Test()
	public void myBookmarksTest() {
		driver.get(myBookmarks);

		String parentWindow = driver.getWindowHandle();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='CPMS1']"))).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='TestRail']"))).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='TL 2.0 Confluence']")))
				.click();
		driverWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='TexturaLink JIRA DashBoard']")))
				.click();
		int i = 0;
		Set<String> windows = driver.getWindowHandles();
		/*
		 * Windows will be stored in reverse order in the Set (whichever
		 * is opened last will be the second one i.e after parent window
		 */
		System.out.println("Total number of windows:" + windows.size());
		for (String win : windows) {
			i++;
			if (i == 4) {
				driver.switchTo().window(win);
				System.out.println("Second window title: " + driver.getTitle());
			}
		}

	}

	@Test(enabled = false)
	public void TestDemo() throws InterruptedException {
		driver.get(baseURl);
		String mainWindow = driver.getWindowHandle();
		By adminPath = By.xpath("//a/small[text()='http://www.phptravels.net/admin']/parent::a");
		By travelsNet = By.xpath("//small[text()='http://www.phptravels.net']/parent::a");
		By submitReq = By.xpath("//small[text()='Submit']//ancestor::a");

//		WebElement element1 = driver.findElement(adminPath);
//		WebElement element2 = driver.findElement(travelsNet);
//		WebElement element3 = driver.findElement(submitReq);

		jsExec.executeScript("window.scrollBy(0,100)");
		WebElement element1 = driverWait.until(ExpectedConditions.visibilityOfElementLocated(adminPath));
		WebElement element2 = driverWait.until(ExpectedConditions.visibilityOfElementLocated(travelsNet));
		// WebElement element3 =
		// driverWait.until(ExpectedConditions.visibilityOfElementLocated(submitReq));

		action.moveToElement(element1).build().perform();
		jsExec.executeScript("arguments[0].scrollIntoView(true);", element2);
		// action.moveToElement(element3).build().perform();

		System.out.println("Current window:" + driver.getWindowHandle());
		action.click(element1).build().perform();
		System.out.println("Current window:" + driver.getWindowHandle());
		jsExec.executeScript("window.scrollBy(0,-100)");
		action.click(element2).build().perform();
		// action.click(element3).build().perform();

		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows.size());
		int i = 0;
		for (String window : windows) {
			i++;
			driver.switchTo().window(window);
			System.out.println("Window " + i + " Title:" + driver.getTitle());
			if (i == 2) {
				driver.close();
				driver.switchTo().window(mainWindow);
				System.out.println("Switched to main window -Title:" + driver.getTitle());
			}
		}
	}

	@Test(enabled = false)
	public void TestDemo2() {
		driver.get(baseURl);
		// SwitchTo will work if there existing windows
		// NavitageTo will open the URL in the window
		driver.switchTo().window("https://google.com/");
		driver.switchTo().window("https://facebook.com/");
		driver.switchTo().window("https://www.sbdbforums.com/");

		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			System.out.println(window);
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}

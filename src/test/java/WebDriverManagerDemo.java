import java.time.Duration;
import java.util.List;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerDemo {

	public static WebDriver driver;
	public static JavascriptExecutor jsDriver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.primevideo.com/");
		// driver.close();
		driver.findElement(By.xpath("//span/a[text()='Sign In']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email"))).sendKeys("gowthampage@gmail.com");
		driver.findElement(By.id("ap_password")).sendKeys("Movies@2020");
		driver.findElement(By.id("signInSubmit")).click();
		jsDriver = (JavascriptExecutor) driver;
		jsDriver.executeScript("window.scrollBy(0,8000);");
		// WebElement moviesBylang= driver.findElement(By.xpath("//h2[text()='Movies by
		// language']//ancestor::div[contains(@class,'tst-faceted')]"));
		WebElement moviesBylang = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h2[text()='Movies by language']//ancestor::div[contains(@class,'tst-faceted')]")));
		// jsDriver.executeScript("arguments[0].scrollIntoView();", moviesBylang);

		List<WebElement> totalLangs = driver.findElements(By.xpath(
				"//h2[text()='Movies by language']//ancestor::div[contains(@class,'tst-faceted')]/div/following-sibling::div//li//div[contains(@class,'tst-pack')]//a"));
		// System.out.println("########### Languages #####################");
		for (WebElement e : totalLangs) {
			String lang = e.getAttribute("aria-label");
			if (lang.equals("English")) {
				jsDriver.executeScript("arguments[0].click();", e);
				break;
			}
		}
		scrollToBottomOfPage();

		List<WebElement> movies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[contains(@class,'search-grid')]//div[contains(@class,'mustache')]//a")));
		System.out.println("Total number of English Movies: " + movies.size());
		for (WebElement e : movies) {
			String movie = e.getText();
			System.out.println(movie);
		}

	}

	private static void scrollToBottomOfPage() {
		long pageHeight = (Long) jsDriver.executeScript("return document.body.scrollHeight;");

		while (true) {
			jsDriver.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long newHeight = (Long) jsDriver.executeScript("return document.body.scrollHeight;");
			if (pageHeight == newHeight) {
				break;
			}

			pageHeight = newHeight;
		}
	}
}

package BrokenLinksandImages;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class BrokenLinksDemo {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.youtube.com/";
	String url = "";
	int respCode;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driverWait = new WebDriverWait(driver,15);
	}

	@Test(enabled = true)
	public void brokenLinksTest() {
		driver.get(baseURl);
		List<WebElement> linkTags = driver.findElements(By.xpath("//a"));
		for (WebElement tag : linkTags) {
			url = tag.getAttribute("href");
			if (url == null || url.isEmpty()) {
				HttpRequest request = new HttpRequest(HttpMethod.GET, url);
//					HttpResponse httpResponse = request.
//					respCode = connection.getResponseCode();
				System.out.println(url + " : ");
				if (respCode >= 400) {
					System.out.print("Link is not valid");
				} else {
					System.out.print("Link is valid");
				}
			}
		}
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}

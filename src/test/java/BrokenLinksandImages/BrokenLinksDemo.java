package BrokenLinksandImages;

import io.restassured.RestAssured;
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
	public String baseURl = "https://amazon.in";

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test(enabled = true)
	public void brokenLinksTest() {
		driver.get(baseURl);
		List<WebElement> linkTags = driver.findElements(By.xpath("//a"));
		for (WebElement tag : linkTags) {
			String url = tag.getAttribute("href");
			if (url != null && !url.isEmpty()) {
				if(RestAssured.given().get(url).statusCode()==200){
						System.out.println("IN Valid URL : "+url);
					}else{
						System.out.println("Valid URL: "+url);
					}
			}
		}

//		linkTags.stream().map(ele->ele.getAttribute("href")).filter(link -> (!link.isEmpty() && !(link !=null))).forEach(
//				link->{
//					if(RestAssured.given().get(url).statusCode()==200){
//						System.out.println("Valid URL : "+url);
//					}else{
//						System.out.println("Invalid URL");
//					}
//				}
//		);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}

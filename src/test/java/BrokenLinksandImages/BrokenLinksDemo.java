package BrokenLinksandImages;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BrokenLinksDemo {

	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://www.youtube.com/";
	String url = "";
	int respCode;

	@BeforeMethod
	public void beforeMethod() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
	}

	@Test(enabled = true)
	public void brokenLinksTest() {
		driver.get(baseURl);
		List<WebElement> linksElements= driver.findElements(By.xpath("//a"));
		
		linksElements.stream()
				.map(e->e.getAttribute("href"))
				.filter(link -> Objects.nonNull(link))
				.forEach(this::checkIfURLisWorking);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	private void checkIfURLisWorking(String url) {
		Response restResponse = RestAssured.given().get(url);
		System.out.println("URL : "+url);
		if (restResponse.getStatusCode() >= 400) {
			System.out.print("Link is not valid \n");
		} else {
			System.out.print("Link is valid \n");
		}
	}
}

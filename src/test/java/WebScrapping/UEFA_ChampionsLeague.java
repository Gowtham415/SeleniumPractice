package WebScrapping;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UEFA_ChampionsLeague {
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	public String baseURl = "https://en.wikipedia.org/wiki/UEFA_Champions_League";
	Map<Long, String> uclWinners;

	@BeforeMethod
	public void beforeMethod() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@Test
	public void uclWinners() {
		uclWinners = new HashMap<Long, String>();
		driver.get(baseURl);
		int rowSize = driverWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//caption[contains(text(),'Performances in the')]//parent::table//tbody//tr")))
				.size();
		for (int i = 0; i < rowSize; i++) {
			String teamName = driver.findElement(By.xpath("//caption[contains(text(),'Performances in the')]//parent::table//tbody//tr["+(i+1)+"]/th/a")).getText();
			List<WebElement> yearsEle = driver
					.findElements(By.xpath("//caption[contains(text(),'Performances in the')]//parent::table//tbody//tr["
							+ (i + 1) + "]//td[3]/a"));
			
			if(yearsEle.size()>0) {
				yearsEle.stream().map(s->s.getText().trim()).forEach(s->uclWinners.put(Long.parseLong(s), teamName));
			}
		}
		
		uclWinners.forEach((k,v)->System.out.println(k+" : "+v));

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}

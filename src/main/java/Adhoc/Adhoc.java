package Adhoc;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Adhoc {			
	static String url = "http://estpmman58.us.oracle.com:8001/tpm/tpm-ui?root=legacyPage&projectID=53803&contractID=12660166&tpmPage=SubcontractListing.psp";
	static WebDriver driver;
	static WebDriverWait wait;
;	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chromer.driver",System.getProperty("user.dir")+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
<<<<<<< HEAD
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
=======
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
>>>>>>> a9c9b26 (Selenium Upgrade and WebTable)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_username|input"))).sendKeys("sapintegration");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_password|input"))).sendKeys("wert66");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign In']//ancestor::button"))).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("cpm-content")));
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='completeSubcontracts']//table[contains(@id,'bodyTable')]//tbody//tr"))).size();
		Thread.sleep(5000);
		int rowCount = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='completeSubcontracts']//table[contains(@id,'bodyTable')]//tbody//tr"))).size();
		System.out.println("RowCount:"+rowCount);
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<=rowCount-1;i++) {
			String subContract = driver.findElement(By.xpath("//div[@id='completeSubcontracts']//table[contains(@id,'bodyTable')]//tbody//tr["+(i+1)+"]//td[2]/a[1]")).getText();
			String vendorID= driver.findElement(By.xpath("//div[@id='completeSubcontracts']//table[contains(@id,'bodyTable')]//tbody//tr["+(i+1)+"]//td[7]")).getText();
			XLUtility.setData("D:\\adhocdata.xlsx", "Sheet1", i, 0, subContract);
			XLUtility.setData("D:\\adhocdata.xlsx", "Sheet1", i, 1, vendorID);
			System.out.println("Running:"+subContract+" : "+vendorID);
			if(map.containsKey(vendorID)) {
				int c = map.get(vendorID);
				c++;
				map.put(vendorID, c);
			}else {
				map.put(vendorID, 1);
			}
			
			XLUtility.setData("D:\\adhocdata.xlsx", "Sheet2", i, 0, vendorID);
			XLUtility.setData("D:\\adhocdata.xlsx", "Sheet2", i, 1, map.get(vendorID).toString());
		}
		
		driver.switchTo().defaultContent();
		//driver.close();
	}
}

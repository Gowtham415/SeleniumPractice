import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class WebDriverDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
		ChromeDriver driver= new ChromeDriver();
		FirefoxDriver fDriver = new FirefoxDriver();
		InternetExplorerDriver ieDriver= new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.expedia.co.in/");
		
		System.out.println(driver.getCurrentUrl());
		
		System.out.println(driver.getTitle());
		
		//Printing all cookies
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println("Cookies:");
		for(Cookie c: cookies) {	
			System.out.println(c.getName()+" : "+c.getValue());
		}
		WebElement element = driver.findElement(By.id("hotel-destination-hp-hotel"));
		//element.getCssValue("color");
		//Taking screenshot of that particulat webelement
		File file = element.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(file, new File("D://file.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dimension dimension = element.getSize();
		Point point = element.getLocation();
		System.out.println("Size of the element:"+dimension.height+" "+dimension.width);
		System.out.println("Location of the element:"+point.getX()+" "+point.getY());
			
		driver.quit();

		try {
			Runtime.getRuntime().exec("D:\\Project15\\chromedriver_kill.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumSingletonDriver {
	
	private static WebDriver driver = null;

	private SeleniumSingletonDriver() {
		
	}
	
	public static WebDriver getDriverInstance() {
		
		if(Objects.isNull(driver)) {
			synchronized(SeleniumSingletonDriver.class) {
				if(Objects.isNull(driver)) {
					return instantiateChromeDriver();
				}
					
			}
		}
		
		return driver;
		
	}
	
	private static WebDriver instantiateChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		options.setBrowserVersion("117");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		return driver;
	}
	
	 
	

}

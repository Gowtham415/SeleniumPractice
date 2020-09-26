package WindowsAndAlerts;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchToAWindow {

	static WebDriver driver;
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("file://D://TexturaWebPage//Textura.html");
		
		driver.findElement(By.xpath("//button[contains(text(),'CPMS1')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'CPMS2')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'CPMS3')]")).click();
		
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		
		ArrayList<String> listOfWindows = new ArrayList<String>(windows);
		System.out.println(listOfWindows.size());
		for(int i=0;i<listOfWindows.size();i++) {
			driver.switchTo().window(listOfWindows.get(i));
			System.out.println(driver.getCurrentUrl());
		}
		
		System.out.println(closeAllOpenWindows(currentWindow));

	}
	
	
	private static boolean closeAllOpenWindows(String currentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		
		for(String window: allWindows) {
			if(!window.equalsIgnoreCase(currentWindow)) {
				driver.switchTo().window(window);
				System.out.println("Closing window: "+driver.getCurrentUrl());
				driver.close();
			}
		}
		
		driver.switchTo().window(currentWindow);
		
		return (driver.getWindowHandles().size()==1)? true :false;
	}

}

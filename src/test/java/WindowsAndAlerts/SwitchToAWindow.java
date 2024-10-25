package WindowsAndAlerts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchToAWindow {

	static WebDriver driver;
	public static void main(String[] args) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("file://D://TexturaWebPage//Textura.html");
		
		driver.findElement(By.xpath("//button[contains(text(),'CPMS1')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'CPMS2')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'CPMS3')]")).click();
		
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		Iterator<String> itr = windows.iterator();

		while(itr.hasNext()){
			driver.switchTo().window(itr.next());
			if(driver.getTitle().equals("so-so")){
				/*
				Your logic
				 */
			}
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

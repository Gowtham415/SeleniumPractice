import org.openqa.selenium.WebDriver;

public class JavaTest {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		System.out.println("Hello World");	
		
		driver = SeleniumSingletonDriver.getDriverInstance();
		driver.get("https://google.com/");
		System.out.println(driver.getTitle());
		driver.close();
	}
}

package ShadowDOM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultipleShadowDOMPractice {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor jsDriver;
    public String baseURl = "https://shop.polymer-project.org/";;
    @BeforeMethod
    public void setUp(){
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
		driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(baseURl);
        jsDriver = (JavascriptExecutor)driver;
    }

    @Test
    public void shadowDOMTest() throws InterruptedException {
        WebElement shadowHost = driver.findElement(By.tagName("shop-app"));
        WebElement ironPages = getShadowRoot(driver,shadowHost).findElement(By.tagName("iron-pages"));
        WebElement secondShadowHost = ironPages.findElement(By.tagName("shop-home"));
        getShadowRoot(driver,secondShadowHost).findElement(By.cssSelector("div:nth-child(2)> shop-button > a")).click();//Mens Wear
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        driver.quit();
    }


    private WebElement getShadowRoot(WebDriver driver, WebElement shadowHost){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot",shadowHost);
    }
}

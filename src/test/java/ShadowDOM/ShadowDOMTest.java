package ShadowDOM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svgelements.SVGElementsPractice;

import java.util.List;

public class ShadowDOMTest {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor jsDriver;
    public String baseURl = "https://books-pwakit.appspot.com/";;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURl);
        jsDriver = (JavascriptExecutor)driver;
    }

    @Test
    public void shadowDOMTest() throws InterruptedException {
        WebElement rootHost = driver.findElement(By.tagName("book-app"));
        WebElement shadowRoot = (WebElement) jsDriver.executeScript("return arguments[0].shadowRoot",rootHost);
        WebElement appHeader = shadowRoot.findElement(By.tagName("app-header"));
        WebElement appToolBar = appHeader.findElement(By.cssSelector("app-toolbar.toolbar-bottom"));
        WebElement bookInputDeco = appToolBar.findElement(By.tagName("book-input-decorator"));
        bookInputDeco.findElement(By.cssSelector("input#input")).sendKeys("Hello World!");
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        driver.quit();
    }
}

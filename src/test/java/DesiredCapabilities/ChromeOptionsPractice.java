package DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;

/*        start-maximized: Opens Chrome in maximize mode
        incognito: Opens Chrome in incognito mode
        headless: Opens Chrome in headless mode
        disable-extensions: Disables existing extensions on Chrome browser
        disable-popup-blocking: Disables pop-ups displayed on Chrome browser
        make-default-browser: Makes Chrome default browser
        version: Prints chrome browser version  */

public class ChromeOptionsPractice {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor jsDriver;
    public String baseURl = "https://www.expedia.co.in/";

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBrowserVersion("117");
        chromeOptions.addArguments("--incognito");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(chromeOptions);
        driverWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        jsDriver = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get(baseURl);
    }
    @Test
    public void test1(){
        driver.findElement(By.xpath("//input[@id='location-field-destination-input']//following-sibling::button"))
                .click();
        driver.findElement(
                By.cssSelector(".uitk-flex-item.uitk-typeahead-toolbar-field.toolbar-content-v1 > div>input"))
                .sendKeys("Hyderabad");
        driver.findElement(By.xpath("//div[@id='location-field-destination-menu']//ul//li[1]/button")).click();
        driver.findElement(By.xpath("//input[@id='d1']/following-sibling::button[1]")).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='10']"))).click();
        driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
        driver.findElement(By.xpath("//input[@id='d2']/following-sibling::button[1]")).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'December')]/following::table[1]//td//button[@data-day='15']"))).click();
        driver.findElement(By.xpath("//span[text()='Done']//ancestor::button")).click();
        driver.findElement(By.xpath("//button[text()='Search']")).click();
    }
    @AfterMethod
    public void tearDown(){

        System.out.println("Hello");
        driver.quit();
    }
}

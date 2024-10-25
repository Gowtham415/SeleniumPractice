package tables;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class WebTableDemo {
    public static WebDriver driver;
    public static JavascriptExecutor jsDriver;
    public static final String tablePathRows="//div[@id='tab1_tableGainer']//tr";
    public static final String tablePathColumns="//div[@id='tab1_tableGainer']//tr[%s]//td";
    public static final String tablePathRowsLosers="//div[@id='tab1_tableLoser']//tr";
    public static final String tablePathColumnsLosers="//div[@id='tab1_tableLoser']//tr[%s]//td";

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("se:name", "Test on Grid - Chrome");

//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setCapability("se:name", "Test on Grid - Chrome");
        driver = new RemoteWebDriver(new URL("http://13.126.112.36:4445"), chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.nseindia.com/");
        printDataFromTable(tablePathRows,tablePathColumns,wait);
        printDataFromTable(tablePathRowsLosers,tablePathColumnsLosers,wait);
        driver.quit();
    }

    public static void printDataFromTable(String rowElement,String columnElement,WebDriverWait wait){
        int numberForRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(rowElement))).size()-1;
//        int numberForRows= driver.findElements(By.xpath(rowElement)).size()-1;
        System.out.println(" SYMBOL | LTP | %CHANGE | VOLUME");
        for(int i=0;i<numberForRows;i++){
//            List<WebElement> webElementList = driver.findElements(By.xpath(String.format(columnElement,i+1)));
            List<WebElement> webElementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(String.format(columnElement,i+1))));
                for(WebElement ele : webElementList){
                    System.out.print(ele.getText().trim()+" ");
                }
            System.out.println();
        }
        System.out.println("##################");
    }
}

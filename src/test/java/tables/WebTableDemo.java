package tables;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WebTableDemo {
    public static WebDriver driver;
    public static JavascriptExecutor jsDriver;
    public static final String tablePathRows="//div[@id='tab1_tableGainer']//tr";
    public static final String tablePathColumns="//div[@id='tab1_tableGainer']//tr[%s]//td";
    public static final String tablePathRowsLosers="//div[@id='tab1_tableLoser']//tr";
    public static final String tablePathColumnsLosers="//div[@id='tab1_tableLoser']//tr[%s]//td";

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.nseindia.com/");
        printDataFromTable(tablePathRows,tablePathColumns);
        printDataFromTable(tablePathRowsLosers,tablePathColumnsLosers);
        driver.quit();
    }

    public static void printDataFromTable(String rowElement,String columnElement){
        int numberForRows= driver.findElements(By.xpath(rowElement)).size()-1;
        System.out.println(" SYMBOL | LTP | %CHANGE | VOLUME");
        for(int i=0;i<numberForRows;i++){
            List<WebElement> webElementList = driver.findElements(By.xpath(String.format(columnElement,i+1)));
                for(WebElement ele : webElementList){
                    System.out.print(ele.getText().trim()+" ");
                }
            System.out.println();
        }
        System.out.println("##################");
    }
}

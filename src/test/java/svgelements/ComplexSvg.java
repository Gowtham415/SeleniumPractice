package svgelements;

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

import java.util.List;

public class ComplexSvg {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor jsDriver;
    public String baseURl = "http://debeissat.nicolas.free.fr/svg3d.php";
    private static Logger log = LogManager.getLogger(SVGElementsPractice.class.getName());
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseURl);
    }

    @Test
    public void svgTest(){
        driver.switchTo().frame("svg_result");
        List<WebElement> elements = driver.findElements(By.xpath("//*[name()='svg']//*[local-name()='g' and @id='g2']//*[local-name()='path' and contains(@id,'face')]"));
        while(true){
            for(WebElement ele:elements){
                String text = null;
                text=ele.getAttribute("d");
                System.out.println(text);
            }
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        driver.quit();
    }
}

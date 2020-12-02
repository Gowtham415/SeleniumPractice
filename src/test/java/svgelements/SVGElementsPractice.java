package svgelements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
    xpaths won't work for svg elements
 */
public class SVGElementsPractice {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor jsDriver;
    public String baseURl = "D:/svg.html";
    private static Logger log = LogManager.getLogger(SVGElementsPractice.class.getName());
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseURl);
    }

    @Test
    public void svgTest(){
       String color =  driver.findElement(By.xpath("//*[name()='svg']//*[local-name()='rect']")).getAttribute("style");
       System.out.println(color);
       String points =  driver.findElement(By.xpath("//*[name()='svg']//*[local-name()='polygon']")).getAttribute("points");
       System.out.printf(points);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        driver.quit();
    }
}

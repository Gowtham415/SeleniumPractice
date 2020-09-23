package com.tpm;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class TPMProjectTest {

	protected WebDriver driver;
	public WebDriverWait driverWait;
	public JavascriptExecutor jsDriver;
	public Actions actions;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, 30);
		jsDriver = (JavascriptExecutor) driver;
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@Test
	public void Test1() throws InterruptedException {
		driver.get("http://estpmman71.us.oracle.com:8001/tpm/tpm-ui?");

		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_username|input"))).sendKeys("gc1");
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_password|input"))).sendKeys("wert66");
		driverWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign In']//ancestor::button")))
				.click();
		waitForPageLoad();
		waitForRowsTobePresent("//table[@aria-label='Project List']//tr");
		int rowCount = driverWait
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//table[@aria-label='Project List']//tr")))
				.size() - 1;
		System.out.println("Number of Rows:" + rowCount);
		for (int i = 0; i < rowCount; i++) {
			List<WebElement> projectNumbers = driver
					.findElements(By.xpath("//table[@aria-label='Project List']//tr[" + i + "]//td[3]//a"));
			if (projectNumbers.size() == 1) {
				String projNum = driver
						.findElements(By.xpath("//table[@aria-label='Project List']//tr[" + i + "]//td[3]//a")).get(0)
						.getText();
				if (projNum.trim().equalsIgnoreCase("56765487")) {
					driver.findElement(By.xpath("//table[@aria-label='Project List']//tr[" + i + "]//td[3]//a"))
							.click();
					break;
				}
			}
		}
		waitForPageLoad();
		WebElement e1 = driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='56765487']//parent::a")));
		actions.moveToElement(e1).build().perform();
		String projectName = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[text()='56765487']//ancestor::div[@class='popover-row']//preceding-sibling::div[@class='popover-row']//div")))
				.getText();
		System.out.println("Project Name is : " + projectName);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			System.out.println("TestCase Failed");
		}
		driver.close();
	}

	public void waitForPageLoad() {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window State       : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public void waitForRowsTobePresent(final String xpath) {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				int rowCount = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)))
						.size() - 1;
				return rowCount > 0 ? true : false;
			}
		});
	}
}

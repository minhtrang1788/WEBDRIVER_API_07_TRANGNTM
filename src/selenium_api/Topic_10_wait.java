package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_wait {
	WebDriver driver;
	Date timeCurrent;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	public void openWebBrowser(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_implicit_wait() {
		openWebBrowser("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		WebElement btStart = driver.findElement(By.xpath("//button"));
		btStart.click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

	}

	public void TC_02_explicit_wait() {
		openWebBrowser("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebElement btStart = driver.findElement(By.xpath("//button"));

		btStart.click();
		// WebElement loadingIcon =
		// driver.findElement(By.xpath(".//*[@id='loading']/img"));
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='loading']/img")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

	}

	public void TC_03_using_explicit_wait() {
		openWebBrowser("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button"))));
		WebElement btStart = driver.findElement(By.xpath("//button"));
		btStart.click();
		// WebElement loadingIcon =
		// driver.findElement(By.xpath(".//*[@id='loading']/img"));

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='finish']/h4"))));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

	}

	
	public void TC_04_countTimeInvisible() {
		openWebBrowser("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button"))));
		WebElement btStart = driver.findElement(By.xpath("//button"));
		btStart.click();
		// WebElement loadingIcon =
		// driver.findElement(By.xpath(".//*[@id='loading']/img"));
		timeCurrent = new Date();
		System.out.println("Time start TC1:" + timeCurrent);
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@id='finish']/h4"))));
		timeCurrent = new Date();
		System.out.println("Time end TC1:" + timeCurrent);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='finish']/h4"))));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

	}
	@Test
	public void TC_04_countTimevisible() {
		openWebBrowser("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button"))));
		WebElement btStart = driver.findElement(By.xpath("//button"));
		btStart.click();
		// WebElement loadingIcon =
		// driver.findElement(By.xpath(".//*[@id='loading']/img"));
		timeCurrent = new Date();
		System.out.println("Time start:" + timeCurrent);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='finish']/h4"))));
		timeCurrent = new Date();
		System.out.println("Time end:" + timeCurrent);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

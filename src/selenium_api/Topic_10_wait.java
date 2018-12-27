package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import com.google.common.base.*;

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
		WebDriverWait wait = new WebDriverWait(driver, 30);
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
		System.out.println("Time start TC2:" + timeCurrent);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='finish']/h4"))));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));
		System.out.println("Time end TC2:" + timeCurrent);
	}

	public void TC_04_countTimevisible() {
		openWebBrowser("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button"))));
		// WebElement btStart = driver.findElement(By.xpath("//button"));
		// btStart.click();
		// WebElement loadingIcon =
		// driver.findElement(By.xpath(".//*[@id='loading']/img"));
		timeCurrent = new Date();
		System.out.println("Time start 2:" + timeCurrent);

		wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//*[@id='finish']"))));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath(".//*[@id='loading']/img"))));
		timeCurrent = new Date();
		System.out.println("Time end 2:" + timeCurrent);

		timeCurrent = new Date();
		System.out.println("Time start:" + timeCurrent);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='loading']"))));
		timeCurrent = new Date();
		System.out.println("Time end:" + timeCurrent);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

	}

	public void TC_05_carlender() {
		openWebBrowser(
				"http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
		WebElement dateSelected = driver.findElement(By.xpath("//span[@class='label']"));

		System.out.println("dateSelected :" + dateSelected.getText());
		WebElement dateSelect = driver.findElement(By.xpath("//a[contains(text(),'24')]"));
		dateSelect.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		dateSelect = driver.findElement(By.xpath("//td[contains(@class,'rcSelected')]//a[contains(text(),'24')]"));
		wait.until(ExpectedConditions.visibilityOf(dateSelect));
		dateSelected = driver.findElement(By.xpath("//span[@class='label']"));
		System.out.println("dateSelected :" + dateSelected.getText());
		Assert.assertTrue(dateSelected.getText().equals("Monday, December 24, 2018"));

	}

	@Test
	public void TC_06_countDown() {
		openWebBrowser("https://daominhdam.github.io/fluent-wait/");
		WebDriverWait wait = new WebDriverWait(driver, 0);
		WebElement countdount = driver.findElement(By.xpath(".//*[@id='javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='javascript_countdown_time']")));
		// Khởi tạo Fluent wait
		new FluentWait<WebElement>(countdount)
				// Tổng time wait là 15s
				.withTimeout(10, TimeUnit.SECONDS)
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(3, TimeUnit.SECONDS)
				// Nếu gặp exception là find ko thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
				// Kiểm tra điều kiện
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						// Kiểm tra điều kiện countdount = 00
						boolean flag = element.getText().endsWith("06");
						System.out.println("Time = " + element.getText());
						// return giá trị cho function apply
						return flag;
					}
				});
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}

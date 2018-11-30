package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Excercise_Iframe_window {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	public void openUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01() {
		openUrl("http://www.hdfcbank.com/");

		WebElement iframeMain = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(iframeMain);
		String messageText = driver.findElement(By.id("messageText")).getText();

		Assert.assertTrue(messageText.equals("What are you looking for?"));
		driver.switchTo().defaultContent();
		WebElement iframeBanner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(iframeBanner);
		List<WebElement> imagesBanner = driver.findElements(By.xpath("//img[@class='bannerimage']"));

		Assert.assertTrue(imagesBanner.size() == 6);
		driver.switchTo().defaultContent();
		List<WebElement> products = driver
				.findElements(By.xpath("//div[@class='flipBanner']//div[contains(@class,'product')]"));
		Assert.assertTrue(products.size() == 8);
	}

	
	public void TC_02() throws InterruptedException {
		openUrl("https://daominhdam.github.io/basic-form/index.html");

		WebElement openWindow = driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));
		openWindow.click();
		Set<String> windows =  driver.getWindowHandles();
		String currentWindow = driver.getWindowHandle();
		for(String wd:windows){
			System.out.println("wd"+wd);
			if(!wd.equals(currentWindow)) {
				driver.switchTo().window(wd);
				Assert.assertTrue(driver.getTitle().equals("Google"));
				Thread.sleep(2000);
				driver.close();
				driver.switchTo().window(currentWindow);
				break;
				
			}
		}
	}
	
	@Test
	public void TC_03() throws InterruptedException {
		openUrl("http://www.hdfcbank.com/");

		WebElement agrLink = driver.findElement(By.xpath("//a[contains(text(),'Agri')]"));
		
		agrLink.click();
		Set<String> windows =  driver.getWindowHandles();
		String currentWindow = driver.getWindowHandle();
		String secondWindow ;
		for(String wd:windows){
			System.out.println("wd"+wd);
			if(!wd.equals(currentWindow)) {
				driver.switchTo().window(wd);
				WebElement accLink = driver.findElement(By.xpath("//p[contains(text(),'Account Details')]//ancestor::a"));
				accLink.click();
				 secondWindow = driver.getWindowHandle();
				Thread.sleep(2000);
				driver.switchTo().window(secondWindow);
				break;
				
			}
		}
		windows =  driver.getWindowHandles();
		for(String wd:windows){
			if(!wd.equals(currentWindow)) {
				driver.switchTo().window(wd);
				driver.close();
			}
		}
		driver.switchTo().window(currentWindow);
	}
	
	@AfterClass
	public void afterClass() {
	//	driver.quit();
	}

}

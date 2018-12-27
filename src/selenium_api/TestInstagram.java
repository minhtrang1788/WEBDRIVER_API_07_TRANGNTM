package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.testng.annotations.AfterClass;

public class TestInstagram {
	WebDriver driver;
	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
	  }
	  
	  public void openWebBrowser(String url) {
		  driver.get(url);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void TC_01_follow() throws InterruptedException {
		  openWebBrowser("https://www.instagram.com");
		  WebElement loginBt = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
		  
		  loginBt.click();
		  driver.findElement(By.xpath("//input[contains(@aria-label,'email')]")).sendKeys("khanhle.t3@gmail.com");
		  driver.findElement(By.xpath("//input[contains(@aria-label,'Password')]")).sendKeys("MinhDan1702");
		  driver.findElement(By.xpath("//button[@type='submit']")).click();
		  driver.findElement(By.xpath("//a[contains(text(),'Not Now')]")).click();
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//button[contains(text(),'Not Now')]")).click();
		  
		  driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("dachshund");
		  driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(Keys.RETURN);
		  List<WebElement> res =  driver.findElements(By.xpath("//div[@class='_2_M76']"));
		//div[@class='_2_M76']
		  
	  }
	  
	  @AfterClass
	  public void afterClass() {
		//  driver.quit();
	  }

}

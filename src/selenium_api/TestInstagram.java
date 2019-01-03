package selenium_api;

import org.testng.annotations.Test;

import com.sun.glass.events.KeyEvent;

import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void TC_01_follow() throws InterruptedException, AWTException {
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
		   WebElement re = res.get(0);
		  re.click();
		  Actions action = new Actions(driver);
		  List<WebElement> likePosts = driver.findElements(By.xpath("//div[contains(@class,'v1Nh3')]"));
		  int count = 0;
		  int maxLike = 0;
		  int numberPost = 0;
		  WebDriverWait wait = new WebDriverWait(driver, 15);
		  for(WebElement post : likePosts) {
			  action.moveToElement(post).perform();
			  count++;
			  WebElement likes =  driver.findElement(By.xpath("//li[@class='-V_eO']//span[not(@class)]"));
			  wait.until(ExpectedConditions.visibilityOf(likes));
			  System.out.println("AAAAAA:"+likes.getText().toString().replace(",", "").replace("k", "000").replace(".", ""));
			  if( Integer.parseInt(likes.getText().toString().replace(",", "").replace("k", "000").replace(".", "")) > maxLike) {
				  maxLike = Integer.parseInt(likes.getText().toString().replace(",", "").replace("k", "000").replace(".", ""));
				  numberPost = count;
			  }
			  
			  if(count > 10) break;
		  }
		  System.out.println("numberPost:"+numberPost + "---maxLike---"+maxLike);
		  likePosts.get(numberPost).click();
		  driver.findElement(By.xpath("//div[@class='e1e1d']")).click();
		  driver.findElement(By.xpath("//a[@class='-nal3 ']")).click();
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='dialog']"))));
		  List<WebElement> followers = driver.findElements(By.xpath("//div[contains(@class,' soMvl')]"));
		  Robot robot = new Robot();
		  for(WebElement follower:followers) {
			  wait.until(ExpectedConditions.visibilityOf(follower));
			  follower.click();
			  
			//  robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			// robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			  Thread.sleep(3000);
		  }
		//div[@class='_2_M76']
		  
	  }
	  
	  @AfterClass
	  public void afterClass() {
		//  driver.quit();
	  }

}

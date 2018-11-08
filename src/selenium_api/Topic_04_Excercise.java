package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_04_Excercise {
	WebDriver driver;
	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
	  }
	  
	  public void openWebBrowser() {
		  driver.get("http://daominhdam.890m.com/");
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void f() {
	  }
	  
	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }

}

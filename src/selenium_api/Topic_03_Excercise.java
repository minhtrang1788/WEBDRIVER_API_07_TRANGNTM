package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Excercise {
	WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	  //driver = new FirefoxDriver();
	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();	
	 
  }
  
  public void openWebBrowser() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test
  public void TC_01_IsDisplayElement() {
	  openWebBrowser();
	  WebElement inputEmail =  driver.findElement(By.xpath("//input[@name='user_email']"));
	  Assert.assertTrue(inputEmail.isDisplayed());
	  WebElement inputUnder18 =  driver.findElement(By.xpath("//input[@value='under_18']"));
	  Assert.assertTrue(inputUnder18.isDisplayed());
	  WebElement inputEducation =  driver.findElement(By.xpath("//textarea[@id='edu']"));
	  Assert.assertTrue(inputEducation.isDisplayed());
	  inputEmail.sendKeys("Automation testing");
	  inputEducation.sendKeys("Automation testing");
	  inputUnder18.click();
	  System.out.println("size:"+driver.manage().window().getSize()); 
  }
  
  @Test
  public void TC_02_IsElementEnabled() {
	  openWebBrowser();
	  checkEnable("//input[@name='user_email']");
	  checkEnable("//input[@value='under_18']");
	  checkEnable("//textarea[@id='edu']");
	  checkEnable("//select[@id='job1']");
	  checkEnable("//input[@value='interest_development']");
	  checkEnable("//input[@id='slider-1']");
	  checkEnable("//button[@id='button-enabled']");
	  checkEnable("//input[@id='password']");
	  checkEnable("//textarea[@id='bio']");
	  checkEnable("//select[@id='job2']");
	  checkEnable("//input[@id='check-disbaled']");
	  checkEnable("//input[@id='slider-2']");
	  checkEnable("//button[@id='button-disabled']");
  }
  
  public void checkEnable(String byXpath) {
	  WebElement element =  driver.findElement(By.xpath(byXpath));
	 
	  if(element.isEnabled()) {
		  Assert.assertTrue(element.isEnabled());
		  System.out.println(byXpath + "is enable");
	  }
	  else {
		  Assert.assertTrue(!element.isEnabled());
		  System.out.println(byXpath + "is disable");
	  }
	  
  }
  @Test
  public void TC_03_IsSelectedElement() {
	  openWebBrowser();
	  WebElement inputUnder18 =  driver.findElement(By.xpath("//input[@id='under_18']"));
	  inputUnder18.click();
	  WebElement inputInterestDev =  driver.findElement(By.xpath("//input[@value='interest_development']"));
	  inputInterestDev.click();
	  if(!inputUnder18.isSelected())
		  inputUnder18.click();
	  if(!inputUnder18.isSelected())
		  inputUnder18.click();
	 
	  
  }
  @AfterClass
  public void afterClass() {
	//  driver.quit();
  }

}

package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Excercise {
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
	  
  }
  
  @Test
  public void TC_02_IsElementEnabled() {
	  openWebBrowser();
	  WebElement inputEmail =  driver.findElement(By.xpath("//input[@name='user_email']"));
	  Assert.assertTrue(inputEmail.isEnabled());
	  WebElement inputUnder18 =  driver.findElement(By.xpath("//input[@value='under_18']"));
	  Assert.assertTrue(inputUnder18.isEnabled());
	  WebElement inputEducation =  driver.findElement(By.xpath("//textarea[@id='edu']"));
	  Assert.assertTrue(inputEducation.isEnabled());
	  WebElement inputJob1 =  driver.findElement(By.xpath("//select[@id='job1']"));
	  Assert.assertTrue(inputJob1.isEnabled());
	  WebElement inputInterestDev =  driver.findElement(By.xpath("//input[@value='interest_development']"));
	  Assert.assertTrue(inputInterestDev.isEnabled());
	  WebElement slider1 =  driver.findElement(By.xpath("//input[@id='slider-1']"));
	  Assert.assertTrue(slider1.isEnabled());
	  WebElement buttonEnable =  driver.findElement(By.xpath("//button[@id='button-enabled']"));
	  Assert.assertTrue(buttonEnable.isEnabled());
	  
	  
	  WebElement inputPassword =  driver.findElement(By.xpath("//input[@id='password']"));
	  Assert.assertTrue(!inputPassword.isEnabled());
	  WebElement inputAge =  driver.findElement(By.xpath("//input[@value='radio-disabled']"));
	  Assert.assertTrue(!inputAge.isEnabled());
	  WebElement inputBiography =  driver.findElement(By.xpath("//textarea[@id='bio']"));
	  Assert.assertTrue(!inputBiography.isEnabled());
	  WebElement inputJob2 =  driver.findElement(By.xpath("//select[@id='job2']"));
	  Assert.assertTrue(!inputJob2.isEnabled());
	  WebElement interestsDisable  =  driver.findElement(By.xpath("//input[@id='check-disbaled']"));
	  Assert.assertTrue(!interestsDisable.isEnabled());
	  WebElement slider2 =  driver.findElement(By.xpath("//input[@id='slider-2']"));
	  Assert.assertTrue(!slider2.isEnabled());
	  WebElement buttonDisable =  driver.findElement(By.xpath("//button[@id='button-disabled']"));
	  Assert.assertTrue(!buttonDisable.isEnabled());
 }
  
  @Test
  public void TC_03_IsSelectedElement() {
	  openWebBrowser();
	  WebElement inputUnder18 =  driver.findElement(By.xpath("//input[@value='under_18']"));
	  inputUnder18.click();
	  WebElement inputInterestDev =  driver.findElement(By.xpath("//input[@id='development']"));
	  inputInterestDev.click();
	  if(!inputUnder18.isSelected())
		  inputUnder18.click();
	  if(!inputUnder18.isSelected())
		  inputUnder18.click();
	 
	  
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}

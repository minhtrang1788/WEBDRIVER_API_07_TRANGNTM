package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.support.ui.Select;

public class TemplateClass {
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
  public void TC_01_Progress_Dropdown_List() {
	  openWebBrowser();
	  Select inputJob1 = new Select( driver.findElement(By.xpath("//select[@id='job1']")));
	  Assert.assertTrue(!inputJob1.isMultiple());
	  inputJob1.selectByVisibleText("Automation Tester");
	  String selectedOption = inputJob1.getFirstSelectedOption().getAttribute("value");
	  System.out.println("selectedOption"+selectedOption);
	  Assert.assertTrue(selectedOption.equals("automation"));
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}

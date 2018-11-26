package selenium_api;

import org.testng.annotations.Test;


import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import  org.openqa.selenium.Alert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_excercise {
	 WebDriver driver;
	 JavascriptExecutor js;
	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
	  }
	  
	  public void openWebBrowser(String url) {
		  driver.get(url);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  
	  public void TC_01() throws InterruptedException {
		  openWebBrowser("http://live.guru99.com/");
		  js_click("//div[@class='footer']//a[contains(text(),'My Account')]");
		  Assert.assertTrue(driver.getCurrentUrl().equals("http://live.guru99.com/index.php/customer/account/login/"));
		  js_click("//a[@title='Create an Account']");
		 
		  Assert.assertTrue(driver.getCurrentUrl().equals("http://live.guru99.com/index.php/customer/account/create/"));
		 
	  }
	  
	  public void TC_02() throws InterruptedException {
		  
		  openWebBrowser("https://demos.telerik.com/kendo-ui/styling/radios");
		  String elementInput = "//label[contains(text(),'2.0 Petrol, 147kW')]//preceding-sibling::input";
		  js_click(elementInput);
		  WebElement checkedRadio = driver.findElement(By.xpath(elementInput));
		  if(!checkedRadio.isSelected())
			  js_click(elementInput);
		  Assert.assertTrue(checkedRadio.isSelected());
		  
	  }
	  
	  public void TC_03() throws InterruptedException {
		  String elementInput = "//label[contains(text(),'Dual-zone air conditioning')]//preceding-sibling::input";
		  openWebBrowser("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		  js_click(elementInput);
		  WebElement checkedRadio = driver.findElement(By.xpath(elementInput));
		
		  if(checkedRadio.isSelected()) {
			  Assert.assertTrue(checkedRadio.isSelected());
			  js_click(elementInput);
		  } 
		  Assert.assertTrue(!checkedRadio.isSelected());
		  
	  }
	  
	  @Test
	  public void TC_04() throws InterruptedException {
		  openWebBrowser("https://daominhdam.github.io/basic-form/index.html");
		  js_click("//button[contains(text(),'Click for JS Alert')]");
		  Alert alert = driver.switchTo().alert();
		  Assert.assertTrue(alert.getText().equals("I am a JS Alert"));
		  alert.accept();
		  WebElement result = driver.findElement(By.xpath("//p[@id='result']"));
		  System.out.println("result.getText() :"+result.getText());
		  Assert.assertTrue(result.getText().equals("You clicked an alert successfully"));
	  }
	  
	  @Test
	  public void TC_05() throws InterruptedException {
		  openWebBrowser("https://daominhdam.github.io/basic-form/index.html");
		  js_click("//button[contains(text(),'Click for JS Confirm')]");
		  Alert alert = driver.switchTo().alert();
		  Assert.assertTrue(alert.getText().equals("I am a JS Confirm"));
		  alert.dismiss();
		  WebElement result = driver.findElement(By.xpath("//p[@id='result']"));
		  System.out.println("result.getText() :"+result.getText());
		  Assert.assertTrue(result.getText().equals("You clicked: Cancel"));
	  }
	  
	  @Test
	  public void TC_06() throws InterruptedException {
		  openWebBrowser("https://daominhdam.github.io/basic-form/index.html");
		  js_click("//button[contains(text(),'Click for JS Prompt')]");
		  Alert alert = driver.switchTo().alert();
		  Assert.assertTrue(alert.getText().equals("I am a JS prompt"));
		  alert.sendKeys("Minh Dan");
		  alert.accept();
		  WebElement result = driver.findElement(By.xpath("//p[@id='result']"));
		  
		  System.out.println("result.getText() :"+result.getText());
		  Assert.assertTrue(result.getText().equals("You entered: Minh Dan"));
	  }
	  
	  @Test
	  public void TC_07() throws InterruptedException {
		  openWebBrowser("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		  WebElement result = driver.findElement(By.xpath("//div[@id='content']/div/p"));
		  Assert.assertTrue(result.getText().contains("Congratulations! You must have the proper credentials."));
	  }
	  
	  public void js_click(String xpathElement) throws InterruptedException {
		  WebElement element = driver.findElement(By.xpath(xpathElement));
		  
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
		  executor.executeScript("arguments[0].click();", element);  
		  //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		  Thread.sleep(3000);
		   
	  }
	  @AfterClass
	  public void afterClass() {
		  //driver.quit();
	  }

}

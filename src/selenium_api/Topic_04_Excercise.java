package selenium_api;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.corba.se.spi.orbutil.fsm.Action;
@Test
public class Topic_04_Excercise {
	WebDriver driver;
	String customerName = "selenium Online";
	String gender = "male";
	String birthday = "2000-10-01";
	String address = "123 Adress";
	String city = "HCM";
	String state = "Thu DUc";
	String pin = "123456";
	String mobileNumber = "01234566788";
	String email = "test@example.com";
	String password = "12345";
	JavascriptExecutor js;
	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		  js = (JavascriptExecutor) driver;
	  }
	  
	  public void openWebBrowser(String url) {
		  driver.get(url);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
	  public void createEditCus(Boolean isCreate,String customerNameInput,String genderInput,String birthdayInput, String addressInput, String cityInput, String stateInput, String pinInput, String mobileNumberInput, String emailInput, String passwordInput) {
		  WebElement nameCus = driver.findElement(By.xpath("//input[@name='name']"));
		  WebElement genderCus = null;
		  WebElement birthdayCus = null;
		  WebElement addressCus = null;
		  WebElement passCus = null;
		  
		  if(isCreate) {
			  if(gender.equals("male"))
				  genderCus = driver.findElement(By.xpath("//input[@value='m']"));
			  else
				  genderCus = driver.findElement(By.xpath("//input[@value='fm']"));
			  birthdayCus = driver.findElement(By.id("dob"));
			  addressCus = driver.findElement(By.xpath("//textarea[@name='addr']"));
		  }
		
		  WebElement cityCus = driver.findElement(By.xpath("//input[@name='city']"));
		  WebElement stateCus = driver.findElement(By.xpath("//input[@name='state']"));
		  WebElement pinCus = driver.findElement(By.xpath("//input[@name='pinno']"));
		  WebElement telCus = driver.findElement(By.xpath("//input[@name='telephoneno']"));
		  email = general.getSaltString();
		  WebElement emailCus = driver.findElement(By.xpath("//input[@name='emailid']"));
		  if(isCreate) {
			  passCus = driver.findElement(By.xpath("//input[@name='password']"));
			  
		  }
		  WebElement subButton = driver.findElement(By.xpath("//input[@name='sub']"));

		  
		  if(isCreate) {
			  nameCus.sendKeys(customerNameInput);
			  genderCus.click();
			  birthdayCus.sendKeys(birthdayInput);
			  addressCus.sendKeys(addressInput);
			  passCus.sendKeys(passwordInput);
		  } else {		  
			  cityCus.clear();
			  stateCus.clear();
			  pinCus.clear();
			  telCus.clear();
			  emailCus.clear();
		  }
		  cityCus.sendKeys(cityInput);
		  stateCus.sendKeys(stateInput);
		  pinCus.sendKeys(pinInput);
		  telCus.sendKeys(mobileNumberInput);
		  emailCus.sendKeys(email);
		  
		  subButton.click();
		  if(isCreate) {
			  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(), customerNameInput);
			  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(), genderInput);
			  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(), birthdayInput);
			  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(), addressInput);
		  }
		  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(), cityInput);
		  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(), stateInput);
		  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(), pinInput);
		  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(), mobileNumberInput);
		  Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(), email);
	  }
	
	  public void TC_01_Progress_textbox() {
		  openWebBrowser("http://demo.guru99.com/v4");
		  WebElement name = driver.findElement(By.xpath("//input[@name='uid']"));
		  WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
		  name.sendKeys("mngr161493");
		  pass.sendKeys("harErAh");
		  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		  driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		 
		  createEditCus(true,customerName ,gender,birthday, address, city, state, pin, mobileNumber, email, password);
		  String cusID =  driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		  System.out.println("Cus ID:"+ cusID);
		 
		  driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
		  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(cusID);
		  driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		  Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), customerName);
		  Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), address);
		  createEditCus(false,"Test automaion" ,"femail",birthday, "DaNang", "DaNang", "VN", "234567", "9999999999", general.getSaltString(), "234567");
	  }
	  public void TC_02_Progress_Dropdown_List() {
		  openWebBrowser("https://daominhdam.github.io/basic-form/index.html");
		  Select inputJob1 = new Select( driver.findElement(By.xpath("//select[@id='job1']")));
		  Assert.assertTrue(!inputJob1.isMultiple());
		  inputJob1.selectByVisibleText("Automation Tester");
		  String selectedOption = inputJob1.getFirstSelectedOption().getAttribute("value");
		  Assert.assertTrue(selectedOption.equals("automation"));
		  inputJob1.selectByValue("manual");
		  selectedOption = inputJob1.getFirstSelectedOption().getAttribute("value");
		  Assert.assertTrue(selectedOption.equals("manual"));
		  inputJob1.selectByIndex(3);
		  selectedOption = inputJob1.getFirstSelectedOption().getAttribute("value");
		  Assert.assertTrue(selectedOption.equals("mobile"));
		  Assert.assertTrue(inputJob1.getOptions().size() == 5);
	  }
	  
	  public void TC_03_Custom_Droplist(){
		  openWebBrowser("http://jqueryui.com/resources/demos/selectmenu/default.html");
		  chose_element_dropdown("0","//span[@id='number-button']","//div[@id='ui-id-19']","19","//span[@id='number-button']//span[@class='ui-selectmenu-text']");
		  chose_element_dropdown("0","//span[@id='number-button']","//div[@id='ui-id-15']","15","//span[@id='number-button']//span[@class='ui-selectmenu-text']");
		  chose_element_dropdown("0","//span[@id='number-button']","//div[@id='ui-id-5']","5","//span[@id='number-button']//span[@class='ui-selectmenu-text']");

		  openWebBrowser("https://material.angular.io/components/select/examples");
		  chose_element_dropdown("//div[contains(text(),'Select with reset option')]","//mat-select[@aria-label='State']","//mat-option[@id='mat-option-20']","Alaska","//mat-select[@aria-label='State']//span[contains(text(),'Alaska')]");
		  chose_element_dropdown("//div[contains(text(),'Select with reset option')]","//mat-select[@aria-label='State']","//mat-option[@id='mat-option-23']","California","//mat-select[@aria-label='State']//span[contains(text(),'California')]");
		  
		 /* openWebBrowser("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		  chose_element_dropdown("0","//span[@aria-owns='color_listbox']","//ul[@id='color_listbox']/li[contains(text(),'Grey')]","Grey","//span[@aria-owns='color_listbox']//span[@class='k-input']");
		  chose_element_dropdown("0","//span[@aria-owns='color_listbox']","//ul[@id='color_listbox']/li[contains(text(),'Orange')]","Orange","//span[@aria-owns='color_listbox']//span[@class='k-input']");*/
	  }
	 
	   public void chose_element_dropdown(String scrollXpath, String drop,String child, String expected,String selectedXpath) {
		   WebElement dropdown =  driver.findElement(By.xpath(drop));
		  
		   if(!scrollXpath.equals("0")) {
			   WebElement scroll =  driver.findElement(By.xpath(scrollXpath));
			   js.executeScript("arguments[0].scrollIntoView(true);", scroll);
		   }
		   
			dropdown.click();
			 // Select listNumber = new Select(driver.findElement(By.xpath("//select[@id='number']")));
			 // listNumber.selectByVisibleText("19");
			  driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
			  driver.findElement(By.xpath(child)).click();
			  WebElement selected = driver.findElement(By.xpath(selectedXpath));
			  System.out.println("selected.getText() :"+selected.getText());
			  Assert.assertTrue(selected.getText().equals(expected));
			 
		   
	   }
	   @Test
	   public void TC_001_Custom_Droplist(){
		   openWebBrowser("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		   	
		   	dropDownCustom("//span[@aria-owns='color_listbox']","//ul[@id='color_listbox']/li","Orange","//span[@aria-owns='color_listbox']//span[@class='k-input']");
		   	dropDownCustom("//span[@aria-owns='color_listbox']","//ul[@id='color_listbox']/li","Grey","//span[@aria-owns='color_listbox']//span[@class='k-input']");
		   	
		   	openWebBrowser("https://mikerodham.github.io/vue-dropdowns/");
		   	openWebBrowser("https://mikerodham.github.io/vue-dropdowns/");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   	dropDownCustom("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//li//a","First Option","//li[@class='dropdown-toggle']");
		   	
		   	openWebBrowser("http://indrimuska.github.io/jquery-editable-select/");
		   	WebElement inputDefault =  driver.findElement(By.xpath("//div[@id='default-place']/input"));
		   	inputDefault.sendKeys("Ford");
		   	inputDefault.sendKeys(Keys.TAB);
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   	String textElement =  driver.findElement(By.xpath("//div[@id='default-place']/input//following-sibling::ul//li[ text()='Ford']")).getAttribute("class");
		    System.out.println("textElement:"+textElement);
		   	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   	Assert.assertTrue(textElement.equals("es-visible selected"));
		   	
		   	
		}
	   public void dropDownCustom(String parent, String children,String checkValue, String selected) {
		   String texxtItem = "";
			 WebElement dropdown =  driver.findElement(By.xpath(parent));
			 dropdown.click();
			 List<WebElement> listChild = driver.findElements(By.xpath(children));
			  for(WebElement child : listChild) {
				  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				  texxtItem = child.getText();
				  System.out.println("text Item:"+texxtItem);
				  if(child.getText().equals(checkValue))
				  {
					  child.click();
					  break;
				  }
			  }
			  WebElement expected =  driver.findElement(By.xpath(selected));
			  System.out.println("expected text :"+expected.getText());
			  Assert.assertTrue(texxtItem.equals(expected.getText()));
			 
		   
	   }
	
	  @AfterClass
	  public void afterClass() {
		 // driver.quit();
	  }

}

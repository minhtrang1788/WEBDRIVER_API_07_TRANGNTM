package selenium_api;

import org.testng.annotations.Test;

import com.sun.java.swing.plaf.windows.resources.windows;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_08_Excercise_Javascript_executer {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	public void goUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	
	public void TC_01_JE() throws InterruptedException {
		goUrl("http://live.guru99.com/");

		String domain = JE_action("return document.domain");
		System.out.println("domain:" + domain);
		Assert.assertTrue(domain.equals("live.guru99.com"));
		String url = JE_action("return document.URL");
		System.out.println("url:" + url);
		Assert.assertTrue(url.equals("http://live.guru99.com/"));
		WebElement mobileLink = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));

		JE_doActionElement(mobileLink);
		Thread.sleep(200);
		WebElement atcSSGalaxy = driver.findElement(By.xpath(
				"//a[@title='Samsung Galaxy' and @class='product-image']//following-sibling::div//button[@title='Add to Cart']"));
		JE_doActionElement(atcSSGalaxy);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String sText = js.executeScript("return document.documentElement.innerText;").toString();
		Assert.assertTrue(sText.contains("Samsung Galaxy was added to your shopping cart."));

		WebElement policyLink = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));

		JE_doActionElement(policyLink);
		String titlePolicy = JE_action("return document.title");
		Assert.assertTrue(titlePolicy.equals("Privacy Policy"));

		js = (JavascriptExecutor) driver;
		js.executeScript("return window.scrollTo(0,document.body.scrollHeight)");

		WebElement wishList = driver
				.findElement(By.xpath("//th[contains(text(),'WISHLIST_CNT')]//following-sibling::td"));
		Assert.assertTrue(wishList.getText().equals("The number of items in your Wishlist."));

		js = (JavascriptExecutor) driver;
		js.executeScript("window.location.href='http://demo.guru99.com/v4'");
		domain = JE_action("return document.domain");
		Assert.assertTrue(domain.equals("demo.guru99.com"));
	}

	
	public void TC_02_Remove_attribute() {
		goUrl("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		if(iframeResult.isDisplayed())
			driver.switchTo().frame(iframeResult);
		WebElement nameInput = driver.findElement(By.xpath("//input[@name='lname']"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('disabled');", nameInput);
		//nameInput.sendKeys("Nguyen");
		js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', 'Nguyen')", nameInput);
		WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
		JE_doActionElement(submit);
		
		
		WebElement res = driver.findElement(By.xpath("//h2[contains(text(),'Your input was received as:')]//following-sibling::div[contains(@class,'w3-container')]"));
		Assert.assertTrue(res.getText().contains("Nguyen"));
	}

	@Test
	public void TC_03_CreateAcc() throws InterruptedException {
		goUrl("http://live.guru99.com/");
		WebElement accLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		
		JE_doActionElement(accLink);
		WebElement createAccLink = driver.findElement(By.xpath("//a[@title = 'Create an Account']"));
		
		
		JE_doActionElement(createAccLink);
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")),"Trang");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='middlename']")),"Minh");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")),"Nguyen");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")),general.getSaltString());
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")),"123456");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")),"123456");
		WebElement btRegister = driver.findElement(By.xpath("//button[@title='Register']"));
		JE_doActionElement(btRegister);
		WebElement message = driver.findElement(By.xpath("//li[@class='success-msg']//li//span"));
		System.out.println("message:"+message.getText());
		Assert.assertTrue(message.getText().equals("Thank you for registering with Main Website Store."));
		WebElement accLinkSkip = driver.findElement(By.xpath("//div[@class='skip-links']//span[contains(text(),'Account')]"));
		JE_doActionElement(accLinkSkip);
		WebElement logOutLink = driver.findElement(By.xpath("//a[@title='Log Out']"));
		JE_doActionElement(logOutLink);
		Thread.sleep(6000);
		//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		String currentLink = JE_action("return document.URL");
		System.out.println("currentLink:"+currentLink);
		Assert.assertTrue(currentLink.equals("http://live.guru99.com/index.php/"));
		
	}
	public String JE_action(String action) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		String res = (String) je.executeScript(action);
		return res;

	}

	public void JE_doActionElement(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
	}
	 public Object sendkeyToElementByJS(WebElement element, String value) {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	        } catch (Exception e) {
	            e.getMessage();
	            return null;
	        }
	    }

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}

package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Topic_01_CheckEnvironment {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Chrome
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();

		// Firefox
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void Verify_Url_and_Title() {

		String temp = "Home page";
		String homePageTitle = driver.getTitle();
		// Assert.assertEquals(homePageTitle, "Home page");
		if (homePageTitle.equals(temp)) {
			System.out.printf("Page Of Title: " + homePageTitle);
		}
	}
	
	@Test
	public void click_account() {
/*
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		System.out.println("click button my account success");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		System.out.println("click button Create an Account success");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.navigate().back();
		String currentUrl = driver.getCurrentUrl();
		System.out.println("current page:"+currentUrl);
		if(currentUrl.equals("http://live.guru99.com/index.php/customer/account/login/"))
			System.out.println("this is login page");
		else System.out.println("this is no login page");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.navigate().forward();
		currentUrl = driver.getCurrentUrl();
		if(currentUrl.equals("http://live.guru99.com/index.php/customer/account/create/"))
			System.out.println("this is create page");
		else System.out.println("this is no create page");
		driver.quit();
		*/
	}
	
	@Test
	public void isLoginEmpty() {
	/*	driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		System.out.println("click button my account success");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String errorPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getAttribute("innerHTML");
		System.out.println("error"+errorPass);
		if(errorPass.equals("This is a required field."))
			System.out.println("passed errorPass message");
		else System.out.println("failed errorPass message");
		String errorEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getAttribute("innerHTML");
		System.out.println("error"+errorEmail);
		if(errorEmail.equals("This is a required field."))
			System.out.println("passed errorEmail message");
		else System.out.println("failed errorEmail message");
		*/
		
		
	}
	@Test
	public void isLoginInvalid() {
	/*
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		System.out.println("click button my account success");
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String errorEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getAttribute("innerHTML");
		System.out.println("error"+errorEmail);
		if(errorEmail.equals("Please enter a valid email address. For example johndoe@domain.com."))
			System.out.println("passed errorEmail message");
		else System.out.println("failed errorEmail message");
		
		*/
	}
	@Test
	public void isLoginInvalidPass() {
	/*
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			System.out.println("click button my account success");
			driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("automation@gmail.com");
			driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123");
			driver.findElement(By.xpath("//button[@title='Login']")).click();
			
			String errorPass = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getAttribute("innerHTML");
			System.out.println("error"+errorPass);
			if(errorPass.equals("Please enter 6 or more characters without leading or trailing spaces."))
				System.out.println("passed errorPass message");
			else System.out.println("failed errorPass message");
		*/	
			
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	@Test
	public void createAnAccount() {
			
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			System.out.println("click button my account success");
			driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("First");
			driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Last");
			driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(getSaltString()+"@example.com");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
			driver.findElement(By.xpath("//button[@title='Register']")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String message = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getAttribute("innerHTML");
			
			System.out.println("error"+message);
			if(message.equals("Thank you for registering with Main Website Store."))
				System.out.println("passed  message");
			else System.out.println("failed  message");
			
			driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
			driver.findElement(By.xpath("//a[@title='Log Out']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String currentUrl = driver.getCurrentUrl();
		//	Assert.assertTrue(currentUrl.equals( "http://live.guru99.com/index.php/"));
			WebElement web = driver.findElement(By.xpath("//button[@title='Subscribe']"));
			;
			System.out.println("height"+web.getCssValue("height"));
			
			
		}

	@AfterClass
	public void afterClass() {
	//	driver.quit();
	}

}

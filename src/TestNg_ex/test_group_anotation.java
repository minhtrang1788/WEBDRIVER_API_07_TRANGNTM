package TestNg_ex;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class test_group_anotation {
	WebDriver driver;

	@Parameters("browser")
	@Test(groups = "group_1", priority = 1)
	public void TC04_getBrowser(String browserName) {
		System.out.println("broserName :" + browserName);
		/*if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("ie")) {

			System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else
			System.out.println("dont have driver");
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
	}

	@Test(groups = "group_1", priority = 2,invocationCount=10,dependsOnMethods = "TC03")
	public void TC01() {
		System.out.println("TC01");
	}

	@Test(groups = "group_5")
	public void TC02() {
		System.out.println("TC02");
	}

	@Test(groups = "group_2", priority = 1)
	public void TC03() {
		System.out.println("TC03");
		Assert.assertEquals("a", "b");
	}
}

package selenium_api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Topic_09_Upload_multipleFile {
	WebDriver driver;
	String filename01 = "D:\\testUpload\\01.png";
	String filename02 = "D:\\testUpload\\02.jpg";
	String filename03 = "D:\\testUpload\\03.png";

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();
	//System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();

	//	System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
	//	driver = new InternetExplorerDriver();
		
	}

	public void openWebBrowser(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	
	public void TC_01_upload_files() throws InterruptedException {
		openWebBrowser("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement input_file = driver.findElement(By.xpath("//input[@type='file']"));

		// WebElement input_file = driver.findElement(By.name("files[]"));
		System.out.println("file:" + filename01 + "\n" + filename02 + "\n" + filename03);
		// input_file.sendKeys('"'+filename01+'"' + '\n'+'"'+filename02+'"' +
		// '\n'+'"'+filename03+'"');
		input_file.sendKeys(filename01 + "\n" + filename02 + "\n" + filename03);

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and contains(text(),'01.png')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and contains(text(),'02.jpg')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and contains(text(),'03.png')]")).isDisplayed());
		WebElement image03 = driver.findElement(By.xpath(
				"//p[@class='name' and contains(text(),'03.png')]/ancestor::tr//button[contains(@class,'start')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image03);
		Thread.sleep(500);

		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//p[@class='name' and contains(text(),'01.png')]/ancestor::tr//button[contains(@class,'start')]"))
				.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(
				"//p[@class='name' and contains(text(),'02.jpg')]/ancestor::tr//button[contains(@class,'start')]"))
				.click();
		Thread.sleep(6000);
		driver.findElement(By.xpath(
				"//p[@class='name' and contains(text(),'03.png')]/ancestor::tr//button[contains(@class,'start')]"))
				.click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='01.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='02.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='03.png']")).isDisplayed());

	}
	

	public void TC_02_autoIT() throws IOException, InterruptedException {
		openWebBrowser("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]")).click();
		String filePath =
				filename01 + "\n" + filename02 + "\n" + filename03;
		System.out.println("filePath:::::::"+filePath);
		Thread.sleep(1000);
		String browserName= driver.toString().toLowerCase();
		if (browserName.contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { ".\\upload\\chrome.exe", filename01 });
		    //driver.findElement(By.cssSelector(".fileinput-button")).click();
		    } else if (browserName.contains("firefox")) {
		    	Runtime.getRuntime().exec(new String[] { ".\\upload\\firefox.exe", filename01 });
		  } else {
			  Runtime.getRuntime().exec(new String[] { ".\\upload\\ie.exe", filename01 });
			  
		  }
		Thread.sleep(500);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and contains(text(),'01.png')]")).isDisplayed());
		driver.findElement(By.xpath(
				"//p[@class='name' and contains(text(),'01.png')]/ancestor::tr//button[contains(@class,'start')]"))
				.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='01.png']")).isDisplayed());
	}

	@Test
	public void TC_03_robotClass() throws IOException, InterruptedException, AWTException {
		openWebBrowser("http://blueimp.github.com/jQuery-File-Upload/");
		
		StringSelection selected = new StringSelection(filename01);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selected, null);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]")).click();
		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(3000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and contains(text(),'01.png')]")).isDisplayed());
		driver.findElement(By.xpath(
				"//p[@class='name' and contains(text(),'01.png')]/ancestor::tr//button[contains(@class,'start')]"))
				.click();
		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='01.png']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}

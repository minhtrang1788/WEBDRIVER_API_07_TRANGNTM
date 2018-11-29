package selenium_api;

import org.testng.annotations.Test;

import org.openqa.selenium.Alert;

import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Excercise_user_interaction {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	public void openWebBrowser(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_Move_Mouse() {
		openWebBrowser("http://www.myntra.com/");
		WebElement element = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		;
		action.click(driver.findElement(By.xpath("//a[contains(text(),'login')]"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}

	public void TC_02_click_hold_element() {
		openWebBrowser(" http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> elements = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
		action.clickAndHold(elements.get(0)).moveToElement(elements.get(9)).release().perform();
		for (WebElement child : elements) {
			if (Integer.parseInt(child.getText()) > 3)
				break;
			System.out.println("selected :" + child.getAttribute("class"));
			Assert.assertTrue(child.getAttribute("class").contains("selected"));
		}
	}

	public void TC_03_click_hold_element() {
		openWebBrowser(" http://www.seleniumlearn.com/double-click");
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Double-Click Me!')]"));
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().equals("The Button was double-clicked."));
		alert.accept();
	}

	public void TC_04_right_click_element() {
		openWebBrowser(" http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));

		Actions action = new Actions(driver);
		action.contextClick(element).perform();
		WebElement quit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(quit).perform();
		Assert.assertTrue(quit.getAttribute("class").contains("context-menu-visible"));
		Assert.assertTrue(quit.getAttribute("class").contains("context-menu-hover"));
		quit.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public void TC_05_drop_drag_element() {
		openWebBrowser("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement element = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Actions action = new Actions(driver);
		action.clickAndHold(element).moveToElement(bigCircle).release().perform();
		Assert.assertTrue(bigCircle.getText().equals("You did great!"));

	}

	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	@Test
	public void TC_06_drop_drag_to_bin() throws IOException {

		openWebBrowser("https://html5demos.com/drag/#");
		String working_dir = System.getProperty("user.dir");
		String js_filepath = working_dir + "/lib/drag_drop.js";
		String jqueryPath = working_dir + "/lib/jquery_load_helper.js";
		String java_script = "";
		String text;
		String jquery = readFile(jqueryPath);
		System.out.println("js_filepath:" + js_filepath);
		BufferedReader input = new BufferedReader(new FileReader(js_filepath));
		StringBuffer buffer = new StringBuffer();

		while ((text = input.readLine()) != null)
			buffer.append(text + " ");
		java_script = buffer.toString();
		System.out.println("java_script:" + java_script);
		input.close();

		List<WebElement> elements = driver.findElements(By.xpath("//ul//li//a"));
		((JavascriptExecutor) driver).executeScript(jquery);
		String idChild = "";
		for (WebElement child : elements) {
			if (child.getText().equals("four"))
				break;
			idChild = child.getAttribute("id");
			System.out.println("idChild:" + idChild);
			java_script = java_script + "$('#" + idChild + "').simulateDragDrop({dropTarget: '#bin'});";
			System.out.println("js:" + java_script);
			((JavascriptExecutor) driver).executeScript(java_script);

		}

	}

	public void excute_js() throws IOException {

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}

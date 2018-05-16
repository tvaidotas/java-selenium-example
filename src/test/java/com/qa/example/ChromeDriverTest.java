package com.qa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;

public class ChromeDriverTest {

	private static WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testGoogleSearch() throws InterruptedException {
		driver.get("http://www.google.com/");
		driver.manage().window().fullscreen();
		Thread.sleep(5000); // Let the user actually see something!
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		WebElement chromeDriverLink = driver.findElement(By.linkText("ChromeDriver - WebDriver for Chrome - Google Sites"));

		// mouse actions begin
		Actions builder = new Actions(driver);
		Point chromeDriverLinkLocation = chromeDriverLink.getLocation();
		builder.moveByOffset(chromeDriverLinkLocation.x + 1, chromeDriverLinkLocation.y + 1).perform();
		Thread.sleep(2000);
		builder.click().perform();
		// mouse actions end

		assertEquals("ChromeDriver - WebDriver for Chrome", driver.getTitle());

		Thread.sleep(5000);
	}

	@Test
	public void draggableMouseActionOne() throws InterruptedException {
		driver.get("http://demoqa.com/");
		driver.manage().window().fullscreen();

		WebElement draggableMenuButton = driver.findElement(By.id("menu-item-140"));
		draggableMenuButton.click();
		Thread.sleep(2000);
		WebElement draggableBox = driver.findElement(By.id("draggable"));

		Actions builder = new Actions(driver);
		Point boxLocation = draggableBox.getLocation();
		builder.clickAndHold(draggableBox).moveByOffset(50,50).perform();
		Thread.sleep(3000);


		//JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		//jsExec.executeScript("document.getElementById('tabs-1').scrollDown += 500");

		//WebElement outerBox = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]"));
		//Thread.sleep(3000);
		//outerBox.sendKeys(Keys.PAGE_DOWN);
		//Thread.sleep(3000);
		//builder.dragAndDropBy(draggableBox,50, 50).perform(); // shorter way

		Thread.sleep(5000);
	}


	@After
	public void tearDown() {
		driver.quit();
	}

}

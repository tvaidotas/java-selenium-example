package com.qa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		builder.moveByOffset(chromeDriverLinkLocation.x + 1, chromeDriverLinkLocation.y + 1).click().perform();
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
		WebElement draggableBox = driver.findElement(By.id("draggable"));

		Actions builder = new Actions(driver);
		Point boxLocation = draggableBox.getLocation();
		builder.clickAndHold(draggableBox).moveByOffset(50,50).perform();
		//builder.dragAndDropBy(draggableBox,50, 50).perform(); // shorter way

		Thread.sleep(5000);
	}


	@After
	public void tearDown() {
		driver.quit();
	}

}

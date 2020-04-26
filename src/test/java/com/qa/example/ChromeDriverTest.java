package com.qa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;

public class ChromeDriverTest {

	private static WebDriver driver;

	@Before
	public void setup() {
		// line above is only needed if we don't place chromedriver.exe in the root directory of the project
		//System.setProperty("webdriver.chrome.driver", "C:/development/web_driver/chromedriver.exe");
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
		WebElement chromeDriverLink = driver
				.findElement(By.partialLinkText("ChromeDriver - WebDriver for Chrome"));
		chromeDriverLink.click();
		Thread.sleep(5000);
	}

	@Test
	public void draggableMouseActionOne() throws InterruptedException {
		driver.get("http://demoqa.com/");
		driver.manage().window().fullscreen();

		WebElement draggableMenuButton = driver.findElement(By.linkText("Draggable"));
		draggableMenuButton.click();
		Thread.sleep(2000);
		WebElement draggableBox = driver.findElement(By.id("draggable"));

		Actions builder = new Actions(driver);
		builder.clickAndHold(draggableBox).moveByOffset(50,50).perform();
		Thread.sleep(3000);
	}


	@After
	public void tearDown() {
		driver.quit();
	}

}

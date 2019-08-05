package com.qa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverTest {

    private static WebDriver driver;

	@Before
	public void setup() {
		// line above is only needed if we don't place chromedriver.exe in the root directory of the project
		//System.setProperty("webdriver.chrome.driver", "C:/development/web_driver/chromedriver.exe");
		driver = new ChromeDriver();
	}

    @Test
    public void exampleSelenium() throws InterruptedException
        {
		driver.manage().window().fullscreen();
        Thread.sleep(1000);
            driver.get("http://www.google.com/");

            Thread.sleep(1000);
            WebElement googleSearchField = driver.findElement(By.name("q"));
            googleSearchField.sendKeys("funny vines compilation");
            Thread.sleep(2000);
		googleSearchField.submit();
            Thread.sleep(2000);
		WebElement funnyVine = driver
                    .findElement(By.partialLinkText("Best Vines of All Time Vine Compilation"));
            funnyVine.click();
            Thread.sleep(15000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

package com.qa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ChromeDriverTest {

    private static WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void exampleSelenium() throws InterruptedException {
        driver.manage().window().fullscreen();
        Thread.sleep(1000);
        driver.get("http://www.google.com/");

        Thread.sleep(1000);
        WebElement googleSearchField = driver.findElement(By.name("q"));
        googleSearchField.sendKeys("funny cat pics");
        Thread.sleep(2000);
        googleSearchField.submit();
        Thread.sleep(2000);
        WebElement pictures = driver.findElement(By.partialLinkText("Images for funny cat"));
        pictures.click();

        Actions builder = new Actions(driver);
        builder.click().moveByOffset(146, 322).perform();
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,197)");
        for (int index = 0; index < 10; index++) {
            WebElement nextButton = driver.findElement(By.cssSelector("#irc-rac > a"));
            nextButton.click();
            //builder.click().moveByOffset(1331, 481).perform();
            Thread.sleep(1500);
        }
        Thread.sleep(15000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

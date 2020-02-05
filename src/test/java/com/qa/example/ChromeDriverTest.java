package com.qa.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

public class ChromeDriverTest {

    private static WebDriver driver;

    @Before
    public void setup() {
        if (isLinux()){
            System.setProperty("webdriver.chrome.driver", "/snap/bin/chromium.chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        }
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("/usr/bin/chromium-browser");
//        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--remote-debugging-port=9222");

        driver = new ChromeDriver(
                (ChromeDriverService)(new ChromeDriverService.Builder() {
                    @Override
                    protected File findDefaultExecutable() {
                        if (new File("/snap/bin/chromium.chromedriver").exists()) {
                            return new File("/snap/bin/chromium.chromedriver") {
                                @Override
                                public String getCanonicalPath() throws IOException {
                                    return this.getAbsolutePath();
                                }
                            };
                        } else {
                            return super.findDefaultExecutable();
                        }
                    }
                }).build() /*, options */);
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

//        Actions builder = new Actions(driver);
//        builder.click().moveByOffset(146, 322).perform();
//        Thread.sleep(1500);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,197)");
//        for (int index = 0; index < 10; index++) {
//            WebElement nextButton = driver.findElement(By.cssSelector("#irc-rac > a"));
//            nextButton.click();
//            //builder.click().moveByOffset(1331, 481).perform();
//            Thread.sleep(1500);
//        }
        Thread.sleep(15000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public boolean isLinux(){
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return false;
        return true;
    }

}

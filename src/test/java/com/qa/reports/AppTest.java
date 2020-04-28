package com.qa.reports;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest {
    ExtentReports report;
    ExtentTest test;
    WebDriver driver;

    // pass scenario
    @Test(priority = 1, enabled = true)
    public void verifyHomePageTitle() {

        // where to create the report file
        report = new ExtentReports("C:\\Users\\tadas\\Desktop\\testResult\\automationreport.html", true);
        // init/start the test
        test = report.startTest("Verify application Title");
        System.setProperty("webdriver.gecko.driver", "C:\\development\\web_driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // add a note to the test
        test.log(LogStatus.INFO, "Browser started");
        driver.get("https://www.qa.com/");
        String title = driver.getTitle();
        System.out.println(title);
        if (title.equals("IT Training | Project Management Training | Business Skills Training | QA")) {
            // report the test as a pass
            test.log(LogStatus.PASS, "verify Title of the page");
        } else {
            test.log(LogStatus.FAIL, "verify Title of the page");
        }
        report.endTest(test);
        report.flush();
    }

    // Fail scenario
    // make this fail to see the screenshot output
    @Test(priority = 2, enabled = true)
    public void verifyLogo() throws IOException {
        test = report.startTest("Verify logo of the application");
        WebElement src = driver.findElement(By.id("Path_582"));
        if (src != null) {
            test.log(LogStatus.PASS, "verify logo");
        } else {
            test.log(LogStatus.FAIL, "verify logo");
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\Users\\tadas\\Desktop\\testResult\\screenshot\\img.jpg"));
            String image = test.addScreenCapture("C:\\Users\\tadas\\Desktop\\testResult\\screenshot\\img.jpg");
            test.log(LogStatus.FAIL, "verify logo of the application", image);
        }
        report.endTest(test);
        report.flush();
        driver.quit();
    }


}

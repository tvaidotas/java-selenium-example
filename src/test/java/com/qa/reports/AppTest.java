package com.qa.reports;

import org.testng.ITestResult;
import org.testng.annotations.*;

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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


/*
    TestNG using selenium and reports
    JUnit had issues with checking whether test passed or failed and putting it into report
 */
public class AppTest {
    ExtentReports report;
    ExtentTest test;
    WebDriver driver;

    @BeforeTest
    public void startReport(){

        report = new ExtentReports (
                System.getProperty("user.dir") + "/test-output/Report.html",
                true
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Environment", "Automated Testing")
                .addSystemInfo("User Name", "Tadas");
        report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
    }

    @Test(priority = 1, enabled = true)
    public void verifyHomePageTitle() {
        test = report.startTest("Verify application Title");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Browser started");
        driver.get("https://www.qa.com/");
        String title = driver.getTitle();
        assertEquals(title, "Virtual and online classes in technology, project management and leadership | QA");
        test.log(LogStatus.PASS, "verify title");
    }

    @Test(priority = 2, enabled = true)
    public void verifyLogo() throws IOException {
        test = report.startTest("Verify application logo is present");
        driver.manage().window().maximize();
        driver.get("https://www.qa.com/");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\test-output\\img.jpg"));
        String image = test.addScreenCapture(System.getProperty("user.dir") + "\\test-output\\img.jpg");
        WebElement src = driver.findElement(By.id("Path_582"));
        assertTrue(src.isDisplayed());
        test.log(LogStatus.PASS, "verify logo", "<img src=img.jpg>");
        test.log(LogStatus.PASS, "verify logo", image); // the way that used to work before I broke it
    }

    @AfterMethod
    public void getResult(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
            test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
        }
        report.endTest(test);
    }

    @AfterTest
    public void endReport(){
        report.flush();
        report.close();
    }

}

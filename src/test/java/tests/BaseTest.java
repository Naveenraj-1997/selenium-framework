package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.beust.jcommander.FuzzyMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import utils.ConfigReader;
import utils.ExtentReportManager;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage products;
    AlertPage alertPage;
    IframePage iframePage;
    WindowsPage windowspage;
    ConfigReader config;
    ExtentReports extent;
    ExtentTest extentTest;

    @BeforeMethod
    public void setup(Method method) throws InterruptedException {
        config = new ConfigReader();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-infobars");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(config.getUrl());
        loginPage = new LoginPage(driver);
        products = new ProductPage(driver);
        alertPage = new AlertPage(driver);
        iframePage = new IframePage(driver);
        windowspage = new WindowsPage(driver);
        extent = ExtentReportManager.getInstance();
        extentTest = ExtentReportManager.createTest(method.getName());
    }
    @AfterMethod
    public void closeBrowser(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus())
        {
            extentTest.fail(result.getThrowable());
            TakesScreenshot ts =(TakesScreenshot) driver;
           File screenshot =  ts.getScreenshotAs(OutputType.FILE);
           try{
               String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
               String testName = result.getTestName();
               FileUtils.copyFile(screenshot, new File("screenshots/"+ testName +"_"+ timeStamp +".png"));
           }catch (IOException e)
           {
               e.printStackTrace();
           }

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test Passed");
        } else {
             extentTest.skip("Test Skipped");
        }
        extent.flush();
        driver.quit();
    }
}

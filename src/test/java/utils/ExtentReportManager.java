package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    static ExtentReports extent;
    static ExtentTest test;

    public static ExtentReports getInstance()
    {
        if(extent == null)
        {
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/TestReport.html");
            reporter.config().setReportName("Automation Report");
            reporter.config().setDocumentTitle("Test Results");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
    public static ExtentTest createTest(String testName)
    {
test = getInstance().createTest(testName);
return test;
    }

    public static ExtentTest getTest() {
        return test;
    }
}

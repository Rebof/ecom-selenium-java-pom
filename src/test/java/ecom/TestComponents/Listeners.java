package ecom.TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ecom.resources.ExtentReportsNG;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReportsNG.getReportObject();
    ExtentTest test;
    private static final Logger logger = LogManager.getLogger(Listeners.class);

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        logger.info("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed");
        logger.info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        logger.error("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        logger.info("All tests finished. Reports generated.");
    }
}
